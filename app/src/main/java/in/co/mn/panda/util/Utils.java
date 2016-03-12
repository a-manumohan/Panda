package in.co.mn.panda.util;

import android.content.Context;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import in.co.mn.panda.R;

/**
 * Created by manuMohan on 11/03/2016.
 */
public class Utils {
    public static String getRecurrenceString(Context context, int recurrence) {
        switch (recurrence) {
            case 0:
                return context.getResources().getString(R.string.recurrence_once);
            case 7:
                return context.getResources().getString(R.string.recurrence_weekly);
            case 14:
                return context.getResources().getString(R.string.recurrence_biweekly);
            case 28:
                return context.getResources().getString(R.string.recurrence_monthly);
            default:
                throw new IllegalArgumentException("Unknown recurrence " + recurrence);
        }
    }

    public static String getFormattedDateString(Context context, String dateString) {
        ZonedDateTime dateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
