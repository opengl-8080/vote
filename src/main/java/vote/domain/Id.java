package vote.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Value
@Embeddable
@RequiredArgsConstructor
public class Id<T> implements Serializable {
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long value;

    @Deprecated
    private Id() {
        this.value = null;
    }
}
