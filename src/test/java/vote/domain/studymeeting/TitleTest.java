package vote.domain.studymeeting;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TitleTest {

    @Test
    public void 値でソート比較できる_比較対象のほうが大きい場合は負数() throws Exception {
        // setup
        Comparable<Title> a = new Title("a");
        Title b = new Title("b");

        // exercise
        int actual = a.compareTo(b);

        // verify
        assertThat(actual).isLessThan(0);
    }

    @Test
    public void 値でソート比較できる_比較対象のほうが小さい場合は正数() throws Exception {
        // setup
        Comparable<Title> b = new Title("b");
        Title a = new Title("a");

        // exercise
        int actual = b.compareTo(a);

        // verify
        assertThat(actual).isGreaterThan(0);
    }

    @Test
    public void 値でソート比較できる_同じ場合は0() throws Exception {
        // setup
        Comparable<Title> a = new Title("a");
        Title same = new Title("a");

        // exercise
        int actual = a.compareTo(same);

        // verify
        assertThat(actual).isEqualTo(0);
    }
}