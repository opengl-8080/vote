package vote.domain.studymeeting;

import org.junit.Before;
import org.junit.Test;
import vote.util.DateUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static vote.test.TestConstants.*;

public class UncompletedStudyMeetingsTest {

    private StudyMeeting studyMeeting1;
    private StudyMeeting studyMeeting2;
    private StudyMeeting studyMeeting3;

    @Before
    public void setUp() throws Exception {
        studyMeeting1 = new StudyMeeting(TITLE1, SUMMARY1);
        studyMeeting2 = new StudyMeeting(TITLE2, SUMMARY2);
        studyMeeting3 = new StudyMeeting(TITLE3, SUMMARY3);
    }

    @Test
    public void 参加希望者の多い勉強会順にソートされていること() throws Exception {
        // setup
        studyMeeting1.add(USER1);

        studyMeeting2.add(USER1);
        studyMeeting2.add(USER2);

        studyMeeting3.add(USER1);
        studyMeeting3.add(USER2);
        studyMeeting3.add(USER3);

        UncompletedStudyMeetings uncompletedStudyMeetings = new UncompletedStudyMeetings(Arrays.asList(studyMeeting1, studyMeeting2, studyMeeting3));

        // exercise
        assertThat(uncompletedStudyMeetings).containsExactly(
            studyMeeting3, studyMeeting2, studyMeeting1
        );
    }

    @Test
    public void 参加希望者数が同数の場合は_参加希望登録日時の最新が新しいもの順にソートされること() throws Exception {
        // setup
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 0);
        studyMeeting1.add(USER1);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 1);
        studyMeeting1.add(USER2);

        DateUtil.fixeNow(2016, 1, 1, 0, 0, 3);
        studyMeeting2.add(USER1);

        DateUtil.fixeNow(2016, 1, 1, 0, 0, 0);
        studyMeeting3.add(USER1);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 2);
        studyMeeting3.add(USER2);

        DateUtil.resetNow();

        UncompletedStudyMeetings uncompletedStudyMeetings = new UncompletedStudyMeetings(Arrays.asList(studyMeeting1, studyMeeting2, studyMeeting3));

        // exercise
        assertThat(uncompletedStudyMeetings).containsExactly(
                studyMeeting3, studyMeeting1, studyMeeting2
        );
    }
}