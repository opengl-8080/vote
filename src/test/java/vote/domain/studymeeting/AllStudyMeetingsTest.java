package vote.domain.studymeeting;

import org.junit.Test;
import vote.util.DateUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static vote.test.TestConstants.*;

public class AllStudyMeetingsTest {

    @Test
    public void 参加希望者の多い勉強会順にソートされていること() throws Exception {
        // setup
        StudyMeeting studyMeeting1 = new StudyMeeting(null, null);
        studyMeeting1.wishJoin(USER1);

        StudyMeeting studyMeeting2 = new StudyMeeting(null, null);
        studyMeeting2.wishJoin(USER1);
        studyMeeting2.wishJoin(USER2);

        StudyMeeting studyMeeting3 = new StudyMeeting(null, null);
        studyMeeting3.wishJoin(USER1);
        studyMeeting3.wishJoin(USER2);
        studyMeeting3.wishJoin(USER3);

        AllStudyMeetings allStudyMeetings = new AllStudyMeetings(Arrays.asList(studyMeeting1, studyMeeting2, studyMeeting3));

        // exercise
        assertThat(allStudyMeetings).containsExactly(
            studyMeeting3, studyMeeting2, studyMeeting1
        );
    }

    @Test
    public void 参加希望者数が同数の場合は_参加希望登録日時の最新が新しいもの順にソートされること() throws Exception {
        // setup
        StudyMeeting studyMeeting1 = new StudyMeeting(null, null);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 0);
        studyMeeting1.wishJoin(USER1);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 1);
        studyMeeting1.wishJoin(USER2);

        StudyMeeting studyMeeting2 = new StudyMeeting(null, null);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 3);
        studyMeeting2.wishJoin(USER1);

        StudyMeeting studyMeeting3 = new StudyMeeting(null, null);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 0);
        studyMeeting3.wishJoin(USER1);
        DateUtil.fixeNow(2016, 1, 1, 0, 0, 2);
        studyMeeting3.wishJoin(USER2);

        DateUtil.resetNow();

        AllStudyMeetings allStudyMeetings = new AllStudyMeetings(Arrays.asList(studyMeeting1, studyMeeting2, studyMeeting3));

        // exercise
        assertThat(allStudyMeetings).containsExactly(
                studyMeeting3, studyMeeting1, studyMeeting2
        );
    }
}