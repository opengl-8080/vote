package vote.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Value
@RequiredArgsConstructor
public class IpAddress {
    @Column(name="USER_IP_ADDRESS")
    String value;

    @Deprecated
    private IpAddress() {
        this.value = null;
    }
}
