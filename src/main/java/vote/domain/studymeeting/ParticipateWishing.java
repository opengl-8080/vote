package vote.domain.studymeeting;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import vote.domain.user.User;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Value
@RequiredArgsConstructor
public class ParticipateWishing {
    @Embedded @NonNull
    private final User user;
    @Embedded @NonNull
    private final RegisterDateTime registerDateTime;

    public String getIpAddressAsString() {
        return this.user.getIpAddressAsString();
    }

    public int compareByRegisterDateTime(ParticipateWishing other) {
        return this.registerDateTime.compareTo(other.registerDateTime);
    }

    @Deprecated
    private ParticipateWishing() {
        this.user = null;
        this.registerDateTime = null;
    }
}
