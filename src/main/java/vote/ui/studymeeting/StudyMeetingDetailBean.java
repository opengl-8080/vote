package vote.ui.studymeeting;

import lombok.Data;
import vote.application.studymeeting.ParticipateStudyMeetingService;
import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.user.User;
import vote.infrastructure.user.CurrentAccessUser;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
@Data
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
        System.out.println("init id=" + this.id);
        this.studyMeeting = this.service.get(new Id<>(this.id));

        this.title = this.studyMeeting.getTitleAsString();
        this.numberOfWishing = this.studyMeeting.getNumberOfWishing().getValue();
        this.summary = this.studyMeeting.getSummaryAsString();

        User user = this.currentAccessUser.get();
        this.participatedByCurrentUser = this.studyMeeting.isParticipatedBy(user);
    }

    public String wishToParticipate() {
        this.service.participate(this.studyMeeting, this.currentAccessUser.get());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "勉強会への参加希望を登録しました。", null));
        return "WEB-INF/complete-modify-participate.xhtml";
    }

    public String cancelToParticipate() {
        this.service.cancel(this.studyMeeting, this.currentAccessUser.get());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "勉強会をキャンセルしました。", null));
        return "WEB-INF/complete-modify-participate.xhtml";
    }
}
