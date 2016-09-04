package vote.infrastructure.studymeeting;

import vote.domain.Id;
import vote.domain.studymeeting.AllStudyMeetings;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
    public AllStudyMeetings findAll() {
        List<StudyMeeting> list = this.em.createNamedQuery("StudyMeeting.findAll", StudyMeeting.class).getResultList();
        return new AllStudyMeetings(list);
    }

    @Override
    public StudyMeeting find(Id<StudyMeeting> id) {
        return this.em.find(StudyMeeting.class, id);
    }

    @Override
    public void delete(StudyMeeting studyMeeting) {
        this.em.remove(studyMeeting);
    }
}
