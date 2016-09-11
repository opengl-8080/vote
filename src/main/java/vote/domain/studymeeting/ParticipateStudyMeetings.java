package vote.domain.studymeeting;

import vote.domain.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 参加勉強会一覧
 */
public class ParticipateStudyMeetings implements Iterable<StudyMeeting> {

    private List<StudyMeeting> list;

    public ParticipateStudyMeetings(User user, List<StudyMeeting> list) {
        this.list = new ArrayList<>(list);
        this.list.sort((sm1, sm2) -> sm1.compareByRegisterDateTimeOfWishingDesc(user, sm2));
    }

    @Override
    public Iterator<StudyMeeting> iterator() {
        return this.list.iterator();
    }
}
