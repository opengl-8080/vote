package vote.domain

/**
 * 参加希望票
 */
class DesiredVotes(
    private val votes: MutableList<Vote> = mutableListOf()
) {
}