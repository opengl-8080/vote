package vote.ui.studymeeting;

import lombok.Data;
import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.user.User;
import vote.infrastructure.user.CurrentAccessUser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@Data
public class StudyMeetingDetailBean {

    private long id;
    private String title;
    private int numberOfWishing;
    private String summary;
    private String submitButtonLabel;
    private String submitButtonClass;
    private String command;

    @Inject
    private StudyMeetingRepository repository;
    @Inject
    private CurrentAccessUser currentAccessUser;

    public void init() {
        System.out.println("init:" + hashCode() + ", id=" + this.id);
        StudyMeeting studyMeeting = this.repository.find(new Id<>(this.id));

        this.title = studyMeeting.getTitleAsString();
        this.numberOfWishing = studyMeeting.getNumberOfWishing().getValue();
        this.summary = studyMeeting.getSummaryAsString();

        User user = this.currentAccessUser.get();

        if (studyMeeting.isWishedToParticipateBy(user)) {
            this.submitButtonLabel = "キャンセルする";
            this.submitButtonClass = "btn-danger";
            this.command = "cancel";
        } else {
            this.submitButtonLabel = "参加を希望する";
            this.submitButtonClass = "btn-primary";
            this.command = "participate";
        }
    }
}
