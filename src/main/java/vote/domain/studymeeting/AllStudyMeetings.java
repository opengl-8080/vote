package vote.domain.studymeeting;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 全勉強会.
 */
public class AllStudyMeetings implements Iterable<StudyMeeting> {

    private final List<StudyMeeting> list;

    public AllStudyMeetings(@NonNull List<StudyMeeting> list) {
        this.list = new ArrayList<>(list);
        this.list.sort(this::compareByNumberOfWishingDescAndMaximumRegisterDateTimeOfWishingDesc);
    }

    /**
     * {@link StudyMeeting} を参加希望人数と最新の参加希望登録日時の降順で比較する.
     * @param a 勉強会１
     * @param b 勉強会２
     * @return 比較結果
     */
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
