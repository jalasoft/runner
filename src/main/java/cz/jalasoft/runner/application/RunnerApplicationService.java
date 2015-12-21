package cz.jalasoft.runner.application;

import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.domain.model.run.*;
import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.domain.model.runner.RunnerRepository;
import cz.jalasoft.runner.domain.model.service.RunningStatistics;
import cz.jalasoft.runner.domain.model.service.RunningStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_DATE;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
@Component
public class RunnerApplicationService {

    private RunnerRepository runnerRepository;
    private RunRepository runRepository;
    private RunningStatisticsService statisticsService;

    @Autowired
    public RunnerApplicationService(RunnerRepository runnerRepository, RunRepository runRepository, RunningStatisticsService statisticsService) {
        this.runnerRepository = runnerRepository;
        this.runRepository = runRepository;
        this.statisticsService = statisticsService;
    }

    public Runner registerRunner(String nickname, String name, String surname, LocalDate birthday) throws RunnerAlreadyExistsException {

        if (runnerRepository.has(nickname)) {
            throw new RunnerAlreadyExistsException(nickname);
        }

        Runner runner = new Runner(nickname, name, surname, birthday);
        runnerRepository.add(runner);

        return runner;
    }

    public Runner getRunner(String nickname) throws NoSuchRunnerException {

        if (!runnerRepository.has(nickname)) {
            throw new NoSuchRunnerException(nickname);
        }

        Runner runner =runnerRepository.ofNickname(nickname);
        return runner;
    }

    public void unregistersRunner(String nickname) throws NoSuchRunnerException {
        if (!runnerRepository.has(nickname)) {
            throw new NoSuchRunnerException(nickname);
        }

        runnerRepository.remove(nickname);
    }

    public Collection<Run> getRuns(String nickname) throws NoSuchRunnerException {
        if (!runnerRepository.has(nickname)) {
            throw new NoSuchRunnerException(nickname);
        }

        Collection<Run> result = runRepository.all(nickname);
        return result;
    }

    public RunningStatistics getStatistics(String nickname) throws NoSuchRunnerException {
        if (!runnerRepository.has(nickname)) {
            throw new NoSuchRunnerException(nickname);
        }

        RunningStatistics statisctics = statisticsService.statistics(nickname, TimeSpan.thisYear());
        return statisctics;
    }

    public void insertRun(String nickname, String dateInIso, double distanceInKilometers, int durationInMinutes) throws NoSuchRunnerException {
        if (!runnerRepository.has(nickname)) {
            throw new NoSuchRunnerException(nickname);
        }

        LocalDate date = parse(dateInIso, ISO_DATE);
        Distance distance = Distance.ofKilometers(distanceInKilometers);
        Duration duration = Duration.ofMinutes(durationInMinutes);
        RunId id = runRepository.nextId();

        Run newRun = new Run(id, nickname, date, distance, duration);
        runRepository.add(newRun);
    }
}
