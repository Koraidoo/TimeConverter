package timeConverter;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {
    private LocalDateTime date = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String fromZone;
    private String toZone;

    public Converter(String fromZone, String toZone) {
        this.fromZone = fromZone;
        this.toZone = toZone;
    }

    public void setDate(int year, int month, int dayOfMonth, int hour, int minute) {
        date = LocalDateTime.of(year, month, dayOfMonth, hour, minute, 0);
    }

    public String convert() throws DateTimeException {
        ZonedDateTime oldTime = date.atZone(ZoneId.of(fromZone));
        ZonedDateTime newTime = oldTime.withZoneSameInstant(ZoneId.of(toZone));
        return newTime.format(formatter);
    }

    /**
     * @return the formatted date
     */
    public String getFormattedDate() {
        return getDate().format(formatter);
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        ZonedDateTime oldTime = date.atZone(ZoneId.of(fromZone));
        return oldTime.toLocalDateTime();
    }
}
