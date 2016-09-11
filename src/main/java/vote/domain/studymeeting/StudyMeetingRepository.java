package vote.domain.studymeeting;

import vote.domain.Id;

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
     * 実施済みの勉強会を取得する.
     * @return 実施済みの勉強会
     */
    CompletedStudyMeetings findCompletedStudyMeetings();

    /**
     * 指定したIDの勉強会を取得する.
     * @param id 勉強会のID
     * @return 該当する勉強会
     * @throws vote.domain.EntityNotFoundException 指定した勉強会が存在しない場合
     */
    StudyMeeting find(Id<StudyMeeting> id);

    /**
     * 指定したIDの勉強会をロックして取得する.
     * @param id 勉強会のID
     * @return 該当する勉強会
     * @throws vote.domain.EntityNotFoundException 指定した勉強会が存在しない場合
     */
    StudyMeeting findWithLock(Id<StudyMeeting> id);

    /**
     * 指定した勉強会をロックする.
     * @param studyMeeting 勉強会
     * @return ロック後の勉強会オブジェクト
     */
    StudyMeeting lock(StudyMeeting studyMeeting);

    /**
     * 指定した勉強会を削除する.
     * @param studyMeeting 削除する勉強会
     */
    void delete(StudyMeeting studyMeeting);
}
