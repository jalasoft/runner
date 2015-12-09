package cz.jalasoft.runner.domain.model.run;

import cz.jalasoft.runner.domain.model.runner.RunnerId;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Run {

    private RunId id;
    private RunnerId runnerId;

    private LocalDate date;
    private Distance distance;
    private Duration duration;

    public LocalDate date() {
        return date;
    }

    public Distance distance() {
        return distance;
    }

    public Duration duration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        Run that = (Run) obj;

        if (!this.date().equals(that.date())) {
            return false;
        }

        if (!this.distance().equals(that.distance())) {
            return false;
        }

        return this.duration().equals(that.duration());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result + date().hashCode();
        result = result + distance().hashCode();
        result = result + duration().hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Run[")
                .append("when=")
                .append(date())
                .append(",distance=")
                .append(distance())
                .append(",duration=")
                .append(duration())
                .append("]")
                .toString();
    }
}
