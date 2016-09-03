package vote.util;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    private static Clock clock = Clock.systemDefaultZone();

    public static void fixeNow(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        ZoneOffset offset = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());

        Clock clock = Clock.fixed(dateTime.toInstant(offset), offset);

        DateUtil.clock = clock;
    }

    public static void resetNow() {
        DateUtil.clock = Clock.systemDefaultZone();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(clock);
    }

    private DateUtil() {}
}
