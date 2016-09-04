package vote.ui.studymeeting;

import lombok.Data;
import vote.application.studymeeting.ParticipateStudyMeetingService;
import vote.domain.Id;
import vote.domain.user.User;
import vote.infrastructure.user.CurrentAccessUser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@Data
public class ModifyParticipateBean {

    private String completeMessage;

    @Inject
    private CurrentAccessUser currentAccessUser;
    @Inject
    private ParticipateStudyMeetingService service;

    public String execute(long id, String command) {
        User user = this.currentAccessUser.get();

        if ("participate".equals(command)) {
            this.service.participate(new Id<>(id), user);
            this.completeMessage = "参加希望";
        } else {
            this.service.cancel(new Id<>(id), user);
            this.completeMessage = "キャンセル";
        }

        return "complete-modify-participate.xhtml";
    }

    public String wishToJoin(long id) {
        System.out.println("wishToJoin:" + hashCode() + ", id=" + id);
        User user = this.currentAccessUser.get();
        this.service.participate(new Id<>(id), user);
        this.completeMessage = "参加希望";

        return "complete-modify-participate.xhtml";
    }

    public String cancelToJoin(long id) {
        System.out.println("cancelToJoin:" + hashCode() + ", id=" + id);
        User user = this.currentAccessUser.get();
        this.service.cancel(new Id<>(id), user);
        this.completeMessage = "キャンセル";

        return "complete-modify-participate.xhtml";
    }

    public enum Command {
        PARTICIPATE,
        CANCEL,
    }
}
