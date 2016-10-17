package cz.jalasoft.myhealth.domain.model.run;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public final class TimeSpan {

    public static TimeSpan thisWeek() {
        LocalDate from = now().with(previousOrSame(DayOfWeek.MONDAY));
        LocalDate to = now();

        return new TimeSpan(from, to);
    }

    public static TimeSpan thisMonth() {
        LocalDate from = now().with(firstDayOfMonth());
        LocalDate to = now();

        return new TimeSpan(from, to);
    }

    public static TimeSpan thisYear() {
        LocalDate from = now().with(firstDayOfYear());
        LocalDate to = now();

        return new TimeSpan(from, to);
    }

    public static TimeSpan between(LocalDate from, LocalDate to) {
        if (from == null) {
            throw new IllegalArgumentException("Date FROM must not be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("Date TO must not be null");
        }

        if (to.isBefore(from)) {
            throw new IllegalArgumentException("TO date must be after FROM date.");
        }

        return new TimeSpan(from, to);
    }

    //-----------------------------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------------------------

    private final LocalDate from;
    private final LocalDate to;

    private TimeSpan(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate from() {
        return from;
    }

    public LocalDate to() {
        return to;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TimeSpan)) {
            return false;
        }

        TimeSpan that = (TimeSpan) obj;

        if (!this.from().equals(that.from())) {
            return false;
        }

        return this.to().equals(that.to());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + from().hashCode();
        result = result * 37 + to().hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("TimeSpan[")
                .append(from())
                .append(" - ")
                .append(to())
                .append("]")
                .toString();
    }
}
