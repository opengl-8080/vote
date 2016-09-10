package vote.domain.studymeeting;

/**
 * 勉強会参加に関する不正な操作が行われたことを表す例外.
 */
public class IllegalParticipateStudyMeetingException extends RuntimeException {

    public IllegalParticipateStudyMeetingException(String message) {
        super(message);
    }
}
