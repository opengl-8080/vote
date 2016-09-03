package vote.sample;

import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;
import vote.domain.user.IpAddress;
import vote.domain.user.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class SampleEjb {

    @PersistenceContext(unitName = "vote-db")
    private EntityManager em;

    public void persist(String t, String s) {
        Title title = new Title(t);
        Summary summary = new Summary(s);

        StudyMeeting studyMeeting = new StudyMeeting(title, summary);

        IpAddress ipAddress = new IpAddress("xxx.yyy.zzz.aaa");
        User user = new User(ipAddress);

        this.em.persist(studyMeeting);

        System.out.println("persist(" + studyMeeting + ")");
    }

    public void showAll() {
        TypedQuery<StudyMeeting> query = this.em.createQuery("select s from StudyMeeting s", StudyMeeting.class);

        for (StudyMeeting studyMeeting : query.getResultList()) {
            System.out.println(studyMeeting);
        }
    }
}
