package cz.jalasoft.runner.application;

import cz.jalasoft.runner.domain.model.run.*;
import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.domain.model.runner.RunnerId;
import cz.jalasoft.runner.domain.model.runner.RunnerRepository;
import cz.jalasoft.runner.domain.model.service.RunningStatistics;
import cz.jalasoft.runner.domain.model.service.RunningStatisticsService;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public class RunnerApplicationService {


    private RunnerRepository runnerRepository;
    private RunRepository runRepository;
    private RunningStatisticsService statisticsService;

    public RunnerApplicationService(RunnerRepository runnerRepository, RunRepository runRepository, RunningStatisticsService statisticsService) {
        this.runnerRepository = runnerRepository;
        this.runRepository = runRepository;
        this.statisticsService = statisticsService;
    }

    public void registerRunner(String nickname, String name, String surname, LocalDate birthday) throws NoSuchRunnerException {

        Runner existingRunner = runnerRepository.ofNickname(nickname);

        if (existingRunner != null) {
            throw new NoSuchRunnerException(nickname);
        }

        RunnerId runnerId = runnerRepository.nextIdentity();
        Runner runner = new Runner(runnerId, nickname, name, surname, birthday);
        runnerRepository.add(runner);
    }

    public void unregistersRunner(String nickname) throws NoSuchRunnerException {
        Runner existingRunner = runnerRepository.ofNickname(nickname);

        if (existingRunner == null) {
            throw new NoSuchRunnerException(nickname);
        }

        runnerRepository.remove(existingRunner.id());
    }

    public Collection<Run> getRuns(String nickname) throws NoSuchRunnerException {
        Runner runner = runnerRepository.ofNickname(nickname);

        if (runner == null) {
            throw new NoSuchRunnerException(nickname);
        }

        Collection<Run> result = runRepository.all(runner.id());
        return result;
    }

    public RunningStatistics getStatistics(String nickname) throws NoSuchRunnerException {
        Runner runner = runnerRepository.ofNickname(nickname);

        if (runner == null) {
            throw new NoSuchRunnerException(nickname);
        }

        RunningStatistics statisctics = statisticsService.statistics(runner.id(), TimeSpan.thisYear());
        return statisctics;
    }

    public void insertNewRun(String nickname, LocalDate when, Distance howMuch, Duration howLong) throws NoSuchRunnerException {

        Runner runner = runnerRepository.ofNickname(nickname);

        if (runner == null) {
            throw new NoSuchRunnerException(nickname);
        }

        RunId id = runRepository.nextId();

        Run newRun = new Run(id, runner.id(), when, howMuch, howLong);
        runRepository.add(newRun);
    }
}
