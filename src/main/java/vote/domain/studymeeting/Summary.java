package vote.domain.studymeeting;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Value
@Embeddable
@RequiredArgsConstructor
public class Summary {
    @Column(name="SUMMARY")
    String value;

    @Deprecated
    private Summary() {
        this.value = null;
    }
}
