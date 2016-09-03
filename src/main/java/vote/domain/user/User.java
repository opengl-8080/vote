package vote.domain.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@EqualsAndHashCode
@ToString
public class User {
    @Embedded
    private final IpAddress ipAddress;

    public User(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Deprecated
    private User() {
        this.ipAddress = null;
    }
}
