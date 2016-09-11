package vote.ui.studymeeting;

import lombok.Value;
import vote.domain.studymeeting.StudyMeeting;

@Value
public class StudyMeetingListItem {
    long id;
    String title;
    int numberOfWishing;
    boolean completed;

    public static StudyMeetingListItem of(StudyMeeting studyMeeting) {
        long id = studyMeeting.getIdAsLong();
        String title = studyMeeting.getTitleAsString();
        int numberOfWishing = studyMeeting.getNumberOfWishing().getValue();

        return new StudyMeetingListItem(id, title, numberOfWishing, studyMeeting.isCompleted());
    }
}
