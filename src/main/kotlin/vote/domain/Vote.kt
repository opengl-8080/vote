package vote.domain

/**
 * 投票
 */
class Vote(
    private val voter: Voter,
    private val dateTime: VotedDateTime
) {
}