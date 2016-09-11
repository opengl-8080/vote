package vote.ui.studymeeting;

import lombok.Data;
import vote.domain.studymeeting.CompletedStudyMeetings;
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
public class CompletedStudyMeetingsBean {

    @Inject
    private StudyMeetingRepository repository;

    public List<StudyMeetingListItem> getStudyMeetingList() {
        List<StudyMeetingListItem> list = new ArrayList<>();

        CompletedStudyMeetings completedStudyMeetings = this.repository.findCompletedStudyMeetings();

        for (StudyMeeting studyMeeting : completedStudyMeetings) {
            StudyMeetingListItem item = StudyMeetingListItem.of(studyMeeting);
            list.add(item);
        }

        return list;
    }
}
