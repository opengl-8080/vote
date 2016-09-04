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

    public void participate(Id<StudyMeeting> id, User user) {
        StudyMeeting studyMeeting = this.repository.find(id);
        studyMeeting.wishJoin(user);
    }

    public void cancel(Id<StudyMeeting> id, User user) {
        StudyMeeting studyMeeting = this.repository.find(id);
        studyMeeting.cancel(user);
    }
}
