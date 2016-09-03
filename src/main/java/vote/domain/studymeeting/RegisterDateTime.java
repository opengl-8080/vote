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

    public static final RegisterDateTime MIN = new RegisterDateTime(LocalDateTime.MIN);

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
