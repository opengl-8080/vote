package vote.ui.studymeeting;

import lombok.Data;
import vote.application.studymeeting.ModifyStudyMeetingService;
import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
@Data
public class ModifyStudyMeetingBean implements Serializable {

    private long id;
    private boolean completed;
    private String title;
    private String summary;

    @Inject
    private StudyMeetingRepository repository;
    @Inject
    private ModifyStudyMeetingService service;

    private StudyMeeting studyMeeting;

    public void init() {
        this.studyMeeting = this.repository.find(new Id<>(this.id));
        this.title = this.studyMeeting.getTitleAsString();
        this.summary = this.studyMeeting.getSummaryAsString();
        this.completed = this.studyMeeting.isCompleted();
    }

    public void modify() throws IOException {
        this.service.modify(this.studyMeeting, new Title(this.title), new Summary(this.summary), this.completed);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "更新しました", null));

        ExternalContext externalContext = context.getExternalContext();
        externalContext.getFlash().setKeepMessages(true);
        HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
        String uri = req.getRequestURI();
        externalContext.redirect(uri + "?id=" + this.id);
    }

    public String delete() {
        this.service.delete(this.studyMeeting);
        return "/study-meeting/complete-delete.xhtml";
    }
}
