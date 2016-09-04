package vote.domain.studymeeting;

import vote.domain.Id;

public interface StudyMeetingRepository {

    /**
     * 指定した勉強会を登録する.
     * @param studyMeeting 勉強会
     */
    void register(StudyMeeting studyMeeting);

    /**
     * 全勉強会を取得する.
     * @return 全勉強会
     */
    AllStudyMeetings findAll();

    /**
     * 指定したIDの勉強会を取得する.
     * @param id 勉強会のID
     * @return 該当する勉強会
     */
    StudyMeeting find(Id<StudyMeeting> id);

    /**
     * 指定した勉強会を削除する.
     * @param studyMeeting 削除する勉強会
     */
    void delete(StudyMeeting studyMeeting);
}
