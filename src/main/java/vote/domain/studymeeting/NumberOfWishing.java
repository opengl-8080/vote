package vote.domain.studymeeting;

import lombok.Value;

@Value
public class NumberOfWishing implements Comparable<NumberOfWishing> {
    int value;

    @Override
    public int compareTo(NumberOfWishing o) {
        return this.value - o.value;
    }
}
