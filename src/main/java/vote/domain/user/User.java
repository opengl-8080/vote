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

    /**
     * このユーザーが管理者であることを確認する.
     * @return ユーザーが管理者の場合は true
     */
    public boolean isAdministrator() {
        return this.ipAddress.isLocalhost();
    }

    @Deprecated
    private User() {
        this.ipAddress = null;
    }

    public String getIpAddressAsString() {
        return this.ipAddress.getValue();
    }
}
