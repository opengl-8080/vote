package vote.ui.studymeeting;

import lombok.Data;
import vote.domain.studymeeting.AllStudyMeetings;
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
public class AllStudyMeetingsBean {

    @Inject
    private StudyMeetingRepository repository;

    public List<StudyMeetingListItem> getStudyMeetingList() {
        List<StudyMeetingListItem> list = new ArrayList<>();

        AllStudyMeetings allStudyMeetings = this.repository.findAll();

        for (StudyMeeting studyMeeting : allStudyMeetings) {
            StudyMeetingListItem item = StudyMeetingListItem.of(studyMeeting);
            list.add(item);
        }

        return list;
    }
}
