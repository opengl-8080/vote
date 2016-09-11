package vote.domain.studymeeting;

import lombok.NonNull;
import lombok.ToString;
import vote.domain.Id;
import vote.domain.user.User;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * 勉強会.
 */
@Entity
@Table(name="STUDY_MEETINGS")
@ToString
public class StudyMeeting implements Serializable {

    @EmbeddedId
    private Id<StudyMeeting> id;
    @Embedded
    private Title title;
    @Embedded
    private Summary summary;
    private boolean completed;
    @Embedded
    @ElementCollection
    @CollectionTable(name="PARTICIPATE_WISHINGS", joinColumns=@JoinColumn(name="STUDY_MEETINGS_ID", referencedColumnName="ID"))
    private List<ParticipateWishing> participateWishingList = new ArrayList<>();
    @Version
    private long version;

    public StudyMeeting(@NonNull Title title, @NonNull Summary summary) {
        this.id = new Id<>(-1L);
        this.title = title;
        this.summary = summary;
    }

    /**
     * 指定したユーザーを、参加希望者として登録する.
     * @param user 参加を希望するユーザー
     */
    public void add(@NonNull User user) {
        if (this.isParticipatedBy(user)) {
            throw new IllegalParticipateStudyMeetingException("指定されたユーザーは既に参加を希望しています（User = " + user + "）");
        }

        ParticipateWishing participateWishing = new ParticipateWishing(user, RegisterDateTime.now());
        this.participateWishingList.add(participateWishing);
    }

    /**
     * 指定したユーザーの参加希望をキャンセルする.
     * @param user 参加希望をキャンセルしたいユーザー
     */
    public void cancel(@NonNull User user) {
        boolean removed = this.participateWishingList.removeIf(it -> it.getUser().equals(user));

        if (!removed) {
            throw new IllegalParticipateStudyMeetingException("指定されたユーザーは参加希望を出していません（User=" + user + "）");
        }
    }

    /**
     * 最新の参加希望の登録日時を取得する.
     * <p>
     * 参加希望が１件も登録されていない場合、空の {@link Optional} が返されます.
     *
     * @return 最新の参加希望の登録日時
     */
    public Optional<RegisterDateTime> getRecentRegisterDateTimeOfWishing() {
        if (this.participateWishingList.isEmpty()) {
            return Optional.empty();
        }

        ParticipateWishing recent = Collections.max(this.participateWishingList, ParticipateWishing::compareByRegisterDateTime);

        return Optional.of(recent.getRegisterDateTime());
    }

    /**
     * この勉強会の参加希望者数を取得する.
     * @return この勉強会の参加希望者数
     */
    public NumberOfWishing getNumberOfWishing() {
        return new NumberOfWishing(this.participateWishingList.size());
    }

    /**
     * 指定した勉強会とタイトルの昇順で比較する.
     * @param other 比較対象の勉強会
     * @return {@link java.util.Comparator Comparator} の仕様に従った比較結果値
     */
    public int compareByTitle(StudyMeeting other) {
        return this.title.compareTo(other.title);
    }


    /**
     * 指定したユーザーが、この勉強会への参加を希望しているかどうか確認する.
     * @param user 参加希望が確認するユーザー
     * @return 参加を希望している場合は true を返す
     */
    public boolean isParticipatedBy(@NonNull User user) {
        return this.participateWishingList.stream().anyMatch(it -> it.getUser().equals(user));
    }

    /**
     * この勉強会を実施済みに変更する.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * この勉強会を未実施に戻す.
     */
    public void reopen() {
        this.completed = false;
    }

    /**
     * この勉強会が実施済みかどうか確認する.
     * @return 実施済みの場合は true
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * タイトルを設定する.
     * @param title タイトル
     */
    public void setTitle(@NonNull Title title) {
        this.title = title;
    }

    /**
     * 概要を設定する.
     * @param summary 概要
     */
    public void setSummary(@NonNull Summary summary) {
        this.summary = summary;
    }

    /**
     * ID を long 値で取得する.
     * @return ID の long 値
     */
    public long getIdAsLong() {
        return this.id.getValue();
    }

    /**
     * タイトルを String 値で取得する.
     * @return タイトルの String 値
     */
    public String getTitleAsString() {
        return this.title.getValue();
    }

    /**
     * 概要を String 値で取得する.
     * @return 概要の String 値
     */
    public String getSummaryAsString() {
        return this.summary.getValue();
    }

    /**
     * 参加希望者の IP アドレスを文字列形式にしたリストを返す.
     * @return 参加者の IP アドレス一覧
     */
    public List<String> getIpAddressStringList() {
        return this.participateWishingList.stream()
                .map(ParticipateWishing::getIpAddressAsString)
                .collect(toList());
    }

    @Deprecated
    private StudyMeeting() {
        this.id = null;
        this.title = null;
        this.summary = null;
    }

    ParticipateWishing getLastAddedWishing() {
        return this.participateWishingList.get(this.participateWishingList.size() - 1);
    }

    /**
     * 指定したユーザーが参加希望を登録した日時で降順に比較する.
     * @param user 参加希望を登録したユーザー
     * @param other 比較対象の勉強会
     * @return 比較結果
     */
    public int compareByRegisterDateTimeOfWishingDesc(User user, StudyMeeting other) {
        ParticipateWishing thisParticipateWishing = this.findParticipateWishing(user);
        ParticipateWishing otherParticipateWishing = other.findParticipateWishing(user);

        return -1 * thisParticipateWishing.compareByRegisterDateTime(otherParticipateWishing);
    }

    private ParticipateWishing findParticipateWishing(User user) {
        return this.participateWishingList.stream()
                    .filter(p -> p.getUser().equals(user))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("参加希望を提出していないユーザーです（" + user + "）"));
    }
}
