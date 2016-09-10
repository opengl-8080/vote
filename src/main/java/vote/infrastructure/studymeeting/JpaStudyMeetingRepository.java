package vote.infrastructure.studymeeting;

import vote.domain.Id;
import vote.domain.studymeeting.UncompletedStudyMeetings;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.EntityNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
