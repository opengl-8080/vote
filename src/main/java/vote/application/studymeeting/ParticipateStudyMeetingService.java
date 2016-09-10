package vote.application.studymeeting;

import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.user.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ParticipateStudyMeetingService {

    @Inject
    private StudyMeetingRepository repository;

    public StudyMeeting get(Id<StudyMeeting> id) {
        return this.repository.find(id);
    }

    public void participate(Id<StudyMeeting> id, User user) {
        StudyMeeting studyMeeting = this.repository.findWithLock(id);
        studyMeeting.add(user);
    }

    public void cancel(Id<StudyMeeting> id, User user) {
        StudyMeeting studyMeeting = this.repository.findWithLock(id);
        studyMeeting.cancel(user);
    }
}
