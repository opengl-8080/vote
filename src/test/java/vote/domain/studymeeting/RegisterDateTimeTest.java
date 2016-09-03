package vote.domain.studymeeting;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class RegisterDateTimeTest {

    @Test
    public void 比較対象のほうが未来の場合は_比較結果は負数になる() throws Exception {
        // setup
        RegisterDateTime one = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 0));
        RegisterDateTime other = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 1));

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isLessThan(0);
    }

    @Test
    public void 比較対象と等しい時刻の場合は_比較結果は0になる() throws Exception {
        // setup
        RegisterDateTime one = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 0));
        RegisterDateTime other = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 0));

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void 比較対象のほうが過去の場合は_比較結果は正数になる() throws Exception {
        // setup
        RegisterDateTime one = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 1));
        RegisterDateTime other = new RegisterDateTime(LocalDateTime.of(2016, 1, 1, 0, 0, 0));

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isGreaterThan(0);
    }
}