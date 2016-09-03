package vote.infrastructure.db.jpa.converter;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

public class LocalDateTimeConverterTest {

    @Test
    public void LocalDateTimeがnullの場合はnullを返す() throws Exception {
        // setup
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // exercise
        Timestamp actual = converter.convertToDatabaseColumn(null);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    public void LocalDateTimeをTimestampに変換できる() throws Exception {
        // setup
        LocalDateTime localDateTime = LocalDateTime.of(2016, 8, 3, 12, 30, 20);
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // exercise
        Timestamp timestamp = converter.convertToDatabaseColumn(localDateTime);

        // verify
        LocalDateTime actual = timestamp.toLocalDateTime();
        assertThat(actual).isEqualTo(localDateTime);
    }

    @Test
    public void Timestampがnullの場合はnullを返す() throws Exception {
        // setup
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // exercise
        LocalDateTime actual = converter.convertToEntityAttribute(null);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    public void TimestampをLocalDateTimeに変換できる() throws Exception {
        // setup
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-12-09 11:24:21");
        Timestamp timestamp = new Timestamp(date.getTime());
        LocalDateTimeConverter converter = new LocalDateTimeConverter();

        // exercise
        LocalDateTime actual = converter.convertToEntityAttribute(timestamp);

        // verify
        assertThat(actual).isEqualTo(LocalDateTime.of(2016, 12, 9, 11, 24, 21));
    }
}