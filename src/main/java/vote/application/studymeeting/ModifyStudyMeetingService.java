package vote.application.studymeeting;

import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ModifyStudyMeetingService {

    @Inject
    private StudyMeetingRepository repository;

    public void modify(StudyMeeting studyMeeting, Title title, Summary summary, boolean completed) {
        StudyMeeting locked = this.repository.lock(studyMeeting);

        locked.setTitle(title);
        locked.setSummary(summary);
        if (completed) {
            locked.complete();
        } else {
            locked.reopen();
        }
    }

    public void delete(StudyMeeting studyMeeting) {
        StudyMeeting locked = this.repository.lock(studyMeeting);
        this.repository.delete(locked);
    }
}
