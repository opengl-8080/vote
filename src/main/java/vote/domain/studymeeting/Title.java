package vote.domain.studymeeting;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Value
@Embeddable
@RequiredArgsConstructor
public class Title implements Comparable<Title> {
    @Column(name="TITLE")
    String value;

    @Deprecated
    private Title() {
        this.value = null;
    }

    @Override
    public int compareTo(Title other) {
        return this.value.compareTo(other.value);
    }
}
