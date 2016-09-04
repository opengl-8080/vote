package vote.domain.studymeeting;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import vote.domain.user.User;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Comparator;

@Embeddable
@Value
@RequiredArgsConstructor
public class ParticipateWishing {
    @Embedded @NonNull
    private final User user;
    @Embedded @NonNull
    private final RegisterDateTime registerDateTime;

    /**
     * {@link ParticipateWishing} を、登録日時で昇順に比較する {@link Comparator} を取得する.
     * @return 登録日時で昇順に比較する {@link Comparator}
     */
    public static Comparator<ParticipateWishing> compareByRegisterDateTime() {
        return (a, b) -> a.registerDateTime.compareTo(b.registerDateTime);
    }

    @Deprecated
    private ParticipateWishing() {
        this.user = null;
        this.registerDateTime = null;
    }
}
