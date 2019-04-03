package util;

import org.omg.CORBA.DATA_CONVERSION;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
    private static final int YEAR_TO_BE_OLD = 2;
    public static boolean isOldProduct(int year) {
        return !(LocalDateTime.now().compareTo(LocalDateTime.now().withYear(year)) <= YEAR_TO_BE_OLD);
    }
}
