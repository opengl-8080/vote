package vote.domain.studymeeting;

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
    @Embedded
    private final User user;
    @Embedded
    private final RegisterDateTime registerDateTime;

    public static final Comparator<ParticipateWishing> compareByRegisterDateTime() {
        return (a, b) -> a.registerDateTime.compareTo(b.registerDateTime);
    }

    @Deprecated
    private ParticipateWishing() {
        this.user = null;
        this.registerDateTime = null;
    }
}
