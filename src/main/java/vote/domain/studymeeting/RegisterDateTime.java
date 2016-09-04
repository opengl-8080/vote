package vote.domain.studymeeting;

import lombok.Value;
import vote.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Value
public class RegisterDateTime implements Comparable<RegisterDateTime> {
    @Column(name="REGISTER_DATETIME")
    LocalDateTime value;

    /** 最小の登録日時 */
    public static final RegisterDateTime MIN = new RegisterDateTime(LocalDateTime.MIN);

    /**
     * 現在日時のインスタンスを生成する.
     * @return 現在日時を持った登録日時
     */
    public static RegisterDateTime now() {
        return new RegisterDateTime(DateUtil.now());
    }

    RegisterDateTime(LocalDateTime value) {
        this.value = value;
    }

    @Deprecated
    private RegisterDateTime() {
        this.value = null;
    }

    @Override
    public int compareTo(RegisterDateTime other) {
        return this.value.compareTo(other.value);
    }
}
