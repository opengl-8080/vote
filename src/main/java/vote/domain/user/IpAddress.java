package vote.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Value
@RequiredArgsConstructor
public class IpAddress {
    public static final IpAddress LOCALHOST = new IpAddress("0:0:0:0:0:0:0:1");

    @Column(name="USER_IP_ADDRESS")
    String value;

    @Deprecated
    private IpAddress() {
        this.value = null;
    }

    /**
     * このアドレスがローカルホストを示すかどうかを確認する.
     * @return このアドレスがローカルホストの場合は true
     */
    public boolean isLocalhost() {
        return "0:0:0:0:0:0:0:1".equals(this.value)
                || "127.0.0.1".equals(this.value);
    }
}
