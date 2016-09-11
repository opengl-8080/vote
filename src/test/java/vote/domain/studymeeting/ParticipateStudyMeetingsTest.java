package vote.domain.studymeeting;

import org.junit.After;
import org.junit.Test;
import vote.domain.user.IpAddress;
import vote.domain.user.User;
import vote.util.DateUtil;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ParticipateStudyMeetingsTest {

    private static final User USER_1 = new User(new IpAddress("1.1.1.1"));
    private static final User USER_2 = new User(new IpAddress("1.1.1.2"));

    @Test
    public void 指定ユーザーが参加希望を提出した日付の降順にソートされていること() throws Exception {
        // setup
        StudyMeeting a = newStudyMeeting();
        addUser(a, USER_1, 59);
        addUser(a, USER_2, 0);

        StudyMeeting b = newStudyMeeting();
        addUser(b, USER_1, 58);
        addUser(b, USER_2, 1);

        StudyMeeting c = newStudyMeeting();
        addUser(c, USER_1, 57);
        addUser(c, USER_2, 2);

        StudyMeeting d = newStudyMeeting();
        addUser(d, USER_1, 56);
        addUser(d, USER_2, 3);

        List<StudyMeeting> list = Arrays.asList(d, b, a, c);

        // exercise
        ParticipateStudyMeetings studyMeetings = new ParticipateStudyMeetings(USER_1, list);

        // verify
        assertThat(studyMeetings).containsExactly(a, b, c, d);
    }

    @After
    public void tearDown() throws Exception {
        DateUtil.resetNow();
    }

    private static StudyMeeting newStudyMeeting() {
        return new StudyMeeting(new Title("abc"), new Summary("xxx"));
    }

    private static void addUser(StudyMeeting studyMeeting, User user, int second) {
        DateUtil.fixeNow(2016, 1, 1, 0, 0, second);
        studyMeeting.add(user);
    }
}
