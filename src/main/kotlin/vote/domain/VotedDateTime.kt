package vote.domain

import java.time.LocalDateTime

/**
 * 投票日時
 */
data class VotedDateTime(private val dateTime: LocalDateTime) {
}