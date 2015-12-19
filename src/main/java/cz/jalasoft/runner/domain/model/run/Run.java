package cz.jalasoft.runner.domain.model.run;

import cz.jalasoft.runner.domain.model.DomainEntity;

import java.time.Duration;
import java.time.LocalDate;

import static java.time.LocalDate.now;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Run extends DomainEntity {

    private RunId id;
    private String nickname;
    private LocalDate date;
    private Distance distance;
    private Duration duration;

    protected Run() {

    }

    public Run(RunId id, String nickname, LocalDate when, Distance howMuch, Duration howLong) {
        setId(id);
        setNickname(nickname);
        setDate(when);
        setDistance(howMuch);
        setDuration(howLong);
    }

    //---------------------------------------------------------------------
    //GETTERS AND SETTERS
    //---------------------------------------------------------------------

    private void setId(RunId id) {
        if (id == null) {
            throw new IllegalArgumentException("Run id must not be null");
        }
        this.id = id;
    }

    private RunId getId() {
        return id;
    }

    private void setNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("Nickname must not be null.");
        }
        if (nickname.isEmpty()) {
            throw new IllegalArgumentException("Nickname must not be empty.");
        }
        this.nickname = nickname;
    }

    private String getNickname() {
        return nickname;
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

    private LocalDate getDate() {
        return date;
    }

    private void setDistance(Distance distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance of a run must not be null.");
        }
        this.distance = distance;
    }

    private Distance getDistance() {
        return distance;
    }

    private void setDuration(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration of a run must not be null.");
        }
        this.duration = duration;
    }

    private Duration getDuration() {
        return duration;
    }

    //---------------------------------------------------------------------
    //PUBLIC INTERFACE
    //---------------------------------------------------------------------

    public String nickname() {
        return nickname;
    }

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
