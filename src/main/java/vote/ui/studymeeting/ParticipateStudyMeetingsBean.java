package vote.ui.studymeeting;

import lombok.Data;
import vote.domain.studymeeting.ParticipateStudyMeetings;
import vote.domain.studymeeting.ParticipateWishing;
import vote.domain.studymeeting.StudyMeeting;
import vote.domain.studymeeting.StudyMeetingRepository;
import vote.domain.studymeeting.UncompletedStudyMeetings;
import vote.infrastructure.user.CurrentAccessUser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
@Data
public class ParticipateStudyMeetingsBean {

    @Inject
    private StudyMeetingRepository repository;
    @Inject
    private CurrentAccessUser currentAccessUser;

    public List<StudyMeetingListItem> getStudyMeetingList() {
        List<StudyMeetingListItem> list = new ArrayList<>();

        ParticipateStudyMeetings participateStudyMeetings = this.repository.findParticipateStudyMeetings(this.currentAccessUser.get());

        for (StudyMeeting studyMeeting : participateStudyMeetings) {
            StudyMeetingListItem item = StudyMeetingListItem.of(studyMeeting);
            list.add(item);
        }

        return list;
    }
}
