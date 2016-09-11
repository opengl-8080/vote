package vote.domain.studymeeting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CompletedStudyMeetingsTest {

    @Test
    public void タイトルの昇順にソートされること() throws Exception {
        // setup
        StudyMeeting a = studyMeeting("a");
        StudyMeeting b = studyMeeting("b");
        StudyMeeting c = studyMeeting("c");
        StudyMeeting d = studyMeeting("d");

        List<StudyMeeting> list = Arrays.asList(d, b, a, c);

        // exercise
        CompletedStudyMeetings studyMeetings = new CompletedStudyMeetings(list);

        // verify
        assertThat(studyMeetings).containsExactly(a, b, c, d);
    }

    private static StudyMeeting studyMeeting(String title) {
        return new StudyMeeting(new Title(title), new Summary("xxx"));
    }
}