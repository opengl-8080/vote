package vote.application.studymeeting;

import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RegisterStudyMeetingService {

    @Inject
    private StudyMeetingRepository repository;

    public void register(Title title, Summary summary) {
        StudyMeeting studyMeeting = new StudyMeeting(title, summary);
        this.repository.register(studyMeeting);
    }
}
