package vote.domain.studymeeting;

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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="STUDY_MEETINGS")
@ToString
public class StudyMeeting implements Serializable {

    @EmbeddedId
    private Id<StudyMeeting> id;
    @Embedded
    private final Title title;
    @Embedded
    private final Summary summary;
    @Embedded
    @ElementCollection
    @CollectionTable(name="PARTICIPATE_WISHINGS", joinColumns=@JoinColumn(name="STUDY_MEETINGS_ID", referencedColumnName="ID"))
    private List<ParticipateWishing> participateWishingList = new ArrayList<>();
    private RegisterDateTime maximumRegisterDateTimeOfWishing;

    public StudyMeeting(Title title, Summary summary) {
        this.id = new Id<>(-1L);
        this.title = title;
        this.summary = summary;
    }

    public void wishJoin(User user) {
        ParticipateWishing participateWishing = new ParticipateWishing(user, RegisterDateTime.now());
        this.participateWishingList.add(participateWishing);
    }

    public Optional<RegisterDateTime> getMaximumRegisterDateTimeOfWishing() {
        Optional<ParticipateWishing> max =
                this.participateWishingList.stream().max(ParticipateWishing.compareByRegisterDateTime());

        return max.map(ParticipateWishing::getRegisterDateTime);
    }

    public NumberOfWishing getNumberOfWishing() {
        return new NumberOfWishing(this.participateWishingList.size());
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
