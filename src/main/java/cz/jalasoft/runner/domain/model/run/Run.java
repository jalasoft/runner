package cz.jalasoft.runner.domain.model.run;

import cz.jalasoft.runner.domain.model.DomainEntity;
import cz.jalasoft.runner.domain.model.runner.RunnerId;

import java.time.Duration;
import java.time.LocalDate;

import static java.time.LocalDate.now;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Run extends DomainEntity {

    private RunId id;
    private RunnerId runnerId;

    private LocalDate date;
    private Distance distance;
    private Duration duration;

    public Run(RunId id, RunnerId runnerId, LocalDate when, Distance howMuch, Duration howLong) {
        setId(id);
        setRunnerId(runnerId);
        setDate(when);
        setDistance(howMuch);
        setDuration(howLong);
    }

    //---------------------------------------------------------------------
    //SETTERS
    //---------------------------------------------------------------------

    private void setId(RunId id) {
        if (id == null) {
            throw new IllegalArgumentException("Run id must not be null");
        }
        this.id = id;
    }

    private void setRunnerId(RunnerId runnerId) {
        if (runnerId == null) {
            throw new IllegalArgumentException("RunnerId must not be null");
        }
        this.runnerId = runnerId;
    }

    private void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date of a run must not be null.");
        }

        if (now().isBefore(date)) {
            throw new IllegalArgumentException("Date of a run must be history.");
        }
        this.date = date;
    }

    private void setDistance(Distance distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance of a run must not be null.");
        }
        this.distance = distance;
    }

    private void setDuration(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration of a run must not be null.");
        }
        this.duration = duration;
    }

    //---------------------------------------------------------------------
    //PUBLIC INTERFACE
    //---------------------------------------------------------------------

    public LocalDate date() {
        return date;
    }

    public Distance distance() {
        return distance;
    }

    public Duration duration() {
        return duration;
    }

    //----------------------------------------------------------------------
    //OBJECT OVERRIDINGS
    //----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        Run that = (Run) obj;

        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result + id.hashCode();

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
