package vote.domain.studymeeting;

import vote.domain.Id;

import java.util.Optional;

public interface StudyMeetingRepository {

    /**
     * 指定した勉強会を登録する.
     * @param studyMeeting 勉強会
     */
    void register(StudyMeeting studyMeeting);

    /**
     * 未実施の勉強会を取得する.
     * @return 未実施の勉強会
     */
    UncompletedStudyMeetings findUncompletedStudyMeetings();

    /**
     * 指定したIDの勉強会を取得する.
     * @param id 勉強会のID
     * @return 該当する勉強会
     */
    Optional<StudyMeeting> find(Id<StudyMeeting> id);

    /**
     * 指定した勉強会を悲観ロックする.
     * @param studyMeeting 勉強会
     * @return 悲観ロック後の勉強会オブジェクト
     */
    StudyMeeting lock(StudyMeeting studyMeeting);

    /**
     * 指定した勉強会を削除する.
     * @param studyMeeting 削除する勉強会
     */
    void delete(StudyMeeting studyMeeting);
}
