package vote.domain.user;

import javax.persistence.Embeddable;

@Embeddable
public class Owner extends User {

    public Owner(IpAddress ipAddress) {
        super(ipAddress);
    }

    @Deprecated
    private Owner() {
        super(null);
    }
}
