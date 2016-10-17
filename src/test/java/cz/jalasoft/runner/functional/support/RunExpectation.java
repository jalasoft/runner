package cz.jalasoft.runner.functional.support;

import cz.jalasoft.myhealth.domain.model.run.Distance;
import cz.jalasoft.myhealth.domain.model.run.Run;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/19/15.
 */
public class RunExpectation {

    public static RunExpectation run() {
        return new RunExpectation();
    }

    private LocalDate date;
    private Distance distance;
    private Duration duration;

    public RunExpectation withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public RunExpectation withDistance(Distance distance) {
        this.distance = distance;
        return this;
    }

    public RunExpectation withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public boolean matches(Run run) {
        boolean dateMatches = dateMatches(run);
        boolean distanceMatches = distanceMatches(run);
        boolean durationMatches = durationMatches(run);

        return dateMatches && distanceMatches && durationMatches;
    }

    private boolean dateMatches(Run run) {
        if (date == null) {
            return true;
        }
        return date.equals(run.date());
    }

    private boolean distanceMatches(Run run) {
        if (distance == null) {
            return true;
        }

        return distance.equals(run.distance());
    }

    private boolean durationMatches(Run run) {
        if (duration == null) {
            return true;
        }
        return duration.equals(run.duration());
    }
}
