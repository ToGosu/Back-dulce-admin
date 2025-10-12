package co.edu.uco.dulceAdmin.crosscuting.helper;

import java.util.Calendar;
import java.util.Date;

public final class DateHelper {

    private DateHelper() {
    }

    public static Date getDefault() {
        return new Date();
    }

    public static Date getDefault(final Date value) {
        return ObjectHelper.getDefault(value, getDefault());
    }

    public static Date addDays(final Date baseDate, final int days) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDefault(baseDate));
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date addMonths(final Date baseDate, final int months) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDefault(baseDate));
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static Date calculateExpirationDate(final Date creationDate, final boolean isShortExpiration) {
        if (isShortExpiration) {
            return addDays(getDefault(creationDate), 2);
        } else {
            return addMonths(getDefault(creationDate), 1);
        }
    }

    public static boolean isExpired(final Date expirationDate) {
        return getDefault(expirationDate).before(new Date());
    }
}
