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
import java.util.List;
import java.util.Optional;

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
    @Version
    private long version;
    @Embedded
    @ElementCollection
    @CollectionTable(name="PARTICIPATE_WISHINGS", joinColumns=@JoinColumn(name="STUDY_MEETINGS_ID", referencedColumnName="ID"))
    private List<ParticipateWishing> participateWishingList = new ArrayList<>();

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
            throw new IllegalArgumentException("指定されたユーザーは既に参加を希望しています（User = " + user + "）");
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
            throw new IllegalArgumentException("指定されたユーザーは参加希望を出していません（User=" + user + "）");
        }
    }

    /**
     * 最新の参加希望の登録日時を取得する.
     * <p>
     * 参加希望が１件も登録されていない場合、空の {@link Optional} が返されます.
     *
     * @return 最新の参加希望の登録日時
     */
    public Optional<RegisterDateTime> getMaximumRegisterDateTimeOfWishing() {
        Optional<ParticipateWishing> max =
                this.participateWishingList.stream().max(ParticipateWishing.compareByRegisterDateTime());

        return max.map(ParticipateWishing::getRegisterDateTime);
    }

    /**
     * この勉強会の参加希望者数を取得する.
     * @return この勉強会の参加希望者数
     */
    public NumberOfWishing getNumberOfWishing() {
        return new NumberOfWishing(this.participateWishingList.size());
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

    @Deprecated
    private StudyMeeting() {
        this.id = null;
        this.title = null;
        this.summary = null;
    }

    ParticipateWishing getLastAddedWishing() {
        return this.participateWishingList.get(this.participateWishingList.size() - 1);
    }
}
