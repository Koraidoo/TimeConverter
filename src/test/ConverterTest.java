package test;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.DateTimeException;

import timeConverter.Converter;

public class ConverterTest {
    @Test
    public void testUTC1() {
        Converter c = new Converter("UTC", "UTC+1");
        c.setDate(2022, 1, 1, 3, 30);
        assertEquals("2022-01-01 03:30", c.getFormattedDate());
        assertEquals("2022-01-01 04:30", c.convert());
    }

    @Test
    public void testUTC18() {
        Converter c = new Converter("UTC", "UTC+18");
        c.setDate(2022, 1, 1, 3, 30);
        assertEquals("2022-01-01 03:30", c.getFormattedDate());
        assertEquals("2022-01-01 21:30", c.convert());
    }

    @Test
    public void testUTC19() {
        Converter c = new Converter("UTC", "UTC+19");
        c.setDate(2022, 1, 1, 3, 30);
        assertEquals("2022-01-01 03:30", c.getFormattedDate());
        assertThrows(DateTimeException.class, () -> c.convert());
    }
}
