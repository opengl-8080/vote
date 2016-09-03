package vote.domain.studymeeting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class NumberOfWishingTest {

    @Test
    public void 比較対象のほうが大きいの場合は_比較結果は負数になる() throws Exception {
        // setup
        NumberOfWishing one = new NumberOfWishing(0);
        NumberOfWishing other = new NumberOfWishing(1);

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isLessThan(0);
    }

    @Test
    public void 比較対象と等しい場合は_比較結果は0になる() throws Exception {
        // setup
        NumberOfWishing one = new NumberOfWishing(1);
        NumberOfWishing other = new NumberOfWishing(1);

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void 比較対象のほうが小さい場合は_比較結果は正数になる() throws Exception {
        // setup
        NumberOfWishing one = new NumberOfWishing(1);
        NumberOfWishing other = new NumberOfWishing(0);

        // exercise
        int actual = one.compareTo(other);

        // verify
        assertThat(actual).isGreaterThan(0);
    }
}