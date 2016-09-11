package vote.ui.studymeeting;

import lombok.Data;
import vote.domain.studymeeting.UncompletedStudyMeetings;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
@Data
public class UncompletedStudyMeetingsBean {

    @Inject
    private StudyMeetingRepository repository;

    public List<StudyMeetingListItem> getStudyMeetingList() {
        List<StudyMeetingListItem> list = new ArrayList<>();

        UncompletedStudyMeetings uncompletedStudyMeetings = this.repository.findUncompletedStudyMeetings();

        for (StudyMeeting studyMeeting : uncompletedStudyMeetings) {
            StudyMeetingListItem item = StudyMeetingListItem.of(studyMeeting);
            list.add(item);
        }

        return list;
    }
}
