package vote.application.studymeeting;

import vote.domain.Id;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.user.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class ParticipateStudyMeetingService {

    @Inject
    private StudyMeetingRepository repository;

    public Optional<StudyMeeting> get(Id<StudyMeeting> id) {
        return this.repository.find(id);
    }

    public void participate(StudyMeeting studyMeeting, User user) {
        StudyMeeting locked = this.repository.lock(studyMeeting);
        locked.add(user);
    }

    public void cancel(StudyMeeting studyMeeting, User user) {
        StudyMeeting locked = this.repository.lock(studyMeeting);
        locked.cancel(user);
    }
}
