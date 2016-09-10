package vote.domain.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    public void ローカルホストの場合は管理者() throws Exception {
        // setup
        User user = new User(IpAddress.LOCALHOST);

        // exercise
        boolean actual = user.isAdministrator();

        // verify
        assertThat(actual).isTrue();
    }

    @Test
    public void ローカルホストでない場合は管理者でない() throws Exception {
        // setup
        User user = new User(new IpAddress("1.1.1.1"));

        // exercise
        boolean actual = user.isAdministrator();

        // verify
        assertThat(actual).isFalse();
    }
}