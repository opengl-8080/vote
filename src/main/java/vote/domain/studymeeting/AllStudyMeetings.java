package vote.domain.studymeeting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllStudyMeetings implements Iterable<StudyMeeting> {

    private final List<StudyMeeting> list;

    public AllStudyMeetings(List<StudyMeeting> list) {
        this.list = new ArrayList<>(list);
        this.list.sort(this::compareByNumberOfWishingDescAndMaximumRegisterDateTimeOfWishingDesc);
    }

    private int compareByNumberOfWishingDescAndMaximumRegisterDateTimeOfWishingDesc(StudyMeeting a, StudyMeeting b) {
        int numberOfWishingOrder = -1 * a.getNumberOfWishing().compareTo(b.getNumberOfWishing());

        if (numberOfWishingOrder != 0) {
            return numberOfWishingOrder;
        }

        RegisterDateTime aRegisterDateTime = a.getMaximumRegisterDateTimeOfWishing().orElse(RegisterDateTime.MIN);
        RegisterDateTime bRegisterDateTime = b.getMaximumRegisterDateTimeOfWishing().orElse(RegisterDateTime.MIN);

        return -1 * aRegisterDateTime.compareTo(bRegisterDateTime);
    }

    @Override
    public Iterator<StudyMeeting> iterator() {
        return this.list.iterator();
    }
}
