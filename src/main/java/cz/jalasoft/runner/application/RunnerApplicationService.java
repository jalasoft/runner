package cz.jalasoft.runner.application;

import cz.jalasoft.runner.domain.model.run.Distance;
import cz.jalasoft.runner.domain.model.run.Run;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public class RunnerApplicationService {

    public void registerRunner(String name, String surname, LocalDate birthday) {

    }

    public Collection<Run> getRuns() {

        return null;
    }

    public void insertNewRun(LocalDate when, Distance where, Duration duration) {

    }
}
