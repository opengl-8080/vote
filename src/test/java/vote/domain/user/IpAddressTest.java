package vote.domain.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class IpAddressTest {

    @Test
    public void ローカルホストを判定できる_IPv6形式の場合() throws Exception {
        // setup
        IpAddress ipAddress = new IpAddress("0:0:0:0:0:0:0:1");

        // exercise
        boolean actual = ipAddress.isLocalhost();

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void ローカルホストを判定できる_IPv4形式の場合() throws Exception {
        // setup
        IpAddress ipAddress = new IpAddress("127.0.0.1");

        // exercise
        boolean actual = ipAddress.isLocalhost();

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void ローカルホストを判定できる_ローカルホストでない場合() throws Exception {
        // setup
        IpAddress ipAddress = new IpAddress("192.168.0.1");

        // exercise
        boolean actual = ipAddress.isLocalhost();

        // verify
        assertThat(actual).isFalse();
    }
}