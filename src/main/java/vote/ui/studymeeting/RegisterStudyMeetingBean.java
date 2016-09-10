package vote.ui.studymeeting;

import lombok.Data;
import vote.application.studymeeting.RegisterStudyMeetingService;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
@Data
public class RegisterStudyMeetingBean {

    private String title;
    private String summary;

    @Inject
    private RegisterStudyMeetingService service;

    public void register() throws IOException {
        this.service.register(new Title(this.title), new Summary(this.summary));

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "登録しました", null));

        ExternalContext externalContext = context.getExternalContext();
        externalContext.getFlash().setKeepMessages(true);
        HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
        String uri = req.getRequestURI();
        externalContext.redirect(uri);
    }
}
