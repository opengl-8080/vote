package vote.domain

/**
 * 勉強会
 */
class StudyMeeting(
    private var name : StudyMeetingName,
    private var summary : Summary
) {
    private var id: Id<StudyMeeting>? = null

    fun setName(name: StudyMeetingName) {
        this.name = name
    }

    fun setSummary(summary: Summary) {
        this.summary = summary
    }
}