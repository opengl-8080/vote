package vote.domain.studymeeting;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 実施済みの全勉強会
 */
public class CompletedStudyMeetings implements Iterable<StudyMeeting> {

    private List<StudyMeeting> studyMeetings;

    public CompletedStudyMeetings(@NonNull List<StudyMeeting> studyMeetings) {
        this.studyMeetings = new ArrayList<>(studyMeetings);
        this.studyMeetings.sort(StudyMeeting::compareByTitle);
    }

    @Override
    public Iterator<StudyMeeting> iterator() {
        return this.studyMeetings.iterator();
    }
}
