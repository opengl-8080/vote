package vote.infrastructure.studymeeting;

import vote.domain.Id;
import vote.domain.studymeeting.CompletedStudyMeetings;
import vote.domain.studymeeting.ParticipateStudyMeetings;
import vote.domain.studymeeting.UncompletedStudyMeetings;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.EntityNotFoundException;
import vote.domain.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class JpaStudyMeetingRepository implements StudyMeetingRepository {

    @Inject
    private EntityManager em;

    @Override
    public void register(StudyMeeting studyMeeting) {
        this.em.persist(studyMeeting);
    }

    @Override
    public UncompletedStudyMeetings findUncompletedStudyMeetings() {
        List<StudyMeeting> list = this.em.createNamedQuery("StudyMeeting.findUncompletedStudyMeetings", StudyMeeting.class).getResultList();
        return new UncompletedStudyMeetings(list);
    }

    @Override
    public CompletedStudyMeetings findCompletedStudyMeetings() {
        List<StudyMeeting> list = this.em.createNamedQuery("StudyMeeting.findCompletedStudyMeetings", StudyMeeting.class).getResultList();
        return new CompletedStudyMeetings(list);
    }

    @Override
    public ParticipateStudyMeetings findParticipateStudyMeetings(User user) {
        TypedQuery<StudyMeeting> query = this.em.createNamedQuery("StudyMeeting.findParticipateStudyMeetings", StudyMeeting.class);
        query.setParameter("user", user);
        List<StudyMeeting> list = query.getResultList();
        return new ParticipateStudyMeetings(user, list);
    }

    @Override
    public StudyMeeting find(Id<StudyMeeting> id) {
        StudyMeeting studyMeeting = this.em.find(StudyMeeting.class, id);
        if (studyMeeting == null) {
            throw new EntityNotFoundException();
        }
        return studyMeeting;
    }

    @Override
    public StudyMeeting findWithLock(Id<StudyMeeting> id) {
        StudyMeeting studyMeeting = this.find(id);
        this.em.lock(studyMeeting, LockModeType.PESSIMISTIC_WRITE);
        return studyMeeting;
    }

    @Override
    public StudyMeeting lock(StudyMeeting studyMeeting) {
        StudyMeeting merged = this.em.merge(studyMeeting);
        this.em.lock(merged, LockModeType.PESSIMISTIC_WRITE);
        return merged;
    }

    @Override
    public void delete(StudyMeeting studyMeeting) {
        this.em.remove(studyMeeting);
    }
}
