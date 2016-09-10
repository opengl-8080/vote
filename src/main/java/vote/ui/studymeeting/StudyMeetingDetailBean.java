package vote.ui.studymeeting;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vote.application.studymeeting.ParticipateStudyMeetingService;
import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.user.User;
import vote.infrastructure.user.CurrentAccessUser;
import vote.ui.NotFoundException;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@ViewScoped
@Named
@Data
@Slf4j
public class StudyMeetingDetailBean implements Serializable {

    private long id;
    private String title;
    private int numberOfWishing;
    private String summary;
    private boolean participatedByCurrentUser;

    @Inject
    private CurrentAccessUser currentAccessUser;
    @Inject
    private ParticipateStudyMeetingService service;

    private StudyMeeting studyMeeting;

    public void init() {
        Optional<StudyMeeting> studyMeeting = this.service.get(new Id<>(this.id));

        this.studyMeeting = studyMeeting.orElseThrow(NotFoundException::new);

        this.title = this.studyMeeting.getTitleAsString();
        this.numberOfWishing = this.studyMeeting.getNumberOfWishing().getValue();
        this.summary = this.studyMeeting.getSummaryAsString();

        User user = this.currentAccessUser.get();
        this.participatedByCurrentUser = this.studyMeeting.isParticipatedBy(user);
    }

    public String wishToParticipate() {
        this.service.participate(this.studyMeeting, this.currentAccessUser.get());
        return "/study-meeting/complete-participate.xhtml?faces-redirect=true";
    }

    public String cancelToParticipate() {
        this.service.cancel(this.studyMeeting, this.currentAccessUser.get());
        return "/study-meeting/complete-cancel.xhtml?faces-redirect=true";
    }
}
