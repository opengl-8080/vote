package vote.ui.studymeeting;

import lombok.Data;
import vote.application.studymeeting.RegisterStudyMeetingService;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@Data
public class RegisterStudyMeetingBean {

    private String title;
    private String summary;
    private String message;

    @Inject
    private RegisterStudyMeetingService service;

    public void register() {
        this.service.register(new Title(this.title), new Summary(this.summary));

        this.title = null;
        this.summary = null;
        this.message = "登録が完了しました";
    }
}
