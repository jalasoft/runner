package cz.jalasoft.runner.application;

import cz.jalasoft.runner.domain.model.run.Distance;
import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.service.RunningStatistics;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public class RunnerApplicationService {


    public void registerRunner(String nickname, String name, String surname, LocalDate birthday) {

    }

    public void unregistersRunner(String nickname) {

    }

    public Collection<Run> getRuns(String nickname) {

        return null;
    }

    public RunningStatistics getStatistics(String nickname) {

        return null;
    }

    public void insertNewRun(String nickname, LocalDate when, Distance howMuch, Duration howLong) {

    }
}
