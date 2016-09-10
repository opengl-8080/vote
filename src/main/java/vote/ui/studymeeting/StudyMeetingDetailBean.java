package vote.ui.studymeeting;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vote.application.studymeeting.ParticipateStudyMeetingService;
import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.user.User;
import vote.infrastructure.user.CurrentAccessUser;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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
    private List<String> ipAddressList;

    @Inject
    private CurrentAccessUser currentAccessUser;
    @Inject
    private ParticipateStudyMeetingService service;

    private StudyMeeting studyMeeting;

    public void init() {
        this.studyMeeting = this.service.get(new Id<>(this.id));

        this.title = this.studyMeeting.getTitleAsString();
        this.numberOfWishing = this.studyMeeting.getNumberOfWishing().getValue();
        this.summary = this.studyMeeting.getSummaryAsString();
        this.ipAddressList = this.studyMeeting.getIpAddressStringList();

        User user = this.currentAccessUser.get();
        this.participatedByCurrentUser = this.studyMeeting.isParticipatedBy(user);
    }

    public String wishToParticipate() {
        this.service.participate(new Id<>(this.id), this.currentAccessUser.get());
        return "/study-meeting/complete-participate.xhtml?faces-redirect=true";
    }

    public String cancelToParticipate() {
        this.service.cancel(new Id<>(this.id), this.currentAccessUser.get());
        return "/study-meeting/complete-cancel.xhtml?faces-redirect=true";
    }
}
