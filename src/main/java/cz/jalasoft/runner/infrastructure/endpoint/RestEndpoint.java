package cz.jalasoft.runner.infrastructure.endpoint;

import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.application.RunnerApplicationService;
import cz.jalasoft.runner.domain.model.run.DistanceUnit;
import cz.jalasoft.runner.domain.model.run.Run;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import static java.util.stream.Collectors.*;
import static java.time.format.DateTimeFormatter.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
@RestController
public class RestEndpoint {

    @Autowired
    private RunnerApplicationService runnerService;

    @RequestMapping(value = "/runner", method = RequestMethod.POST)
    public ResponseEntity<?> registerRunner(@RequestBody RunnerResource runner) {
        LocalDate birthdate = LocalDate.parse(runner.getBirthday(), ISO_DATE);

        try {
            runnerService.registerRunner(
                    runner.getNickname(),
                    runner.getFirstName(),
                    runner.getLastName(),
                    birthdate);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RunnerAlreadyExistsException exc) {
            throw new RunnerResourceAlreadyExistsException(exc);
        }
    }

    @RequestMapping(value = "/runner/{nickname}/", method = RequestMethod.DELETE)
    public ResponseEntity<Object> unregisterRunner(@PathVariable String nickname) {
        try {
            runnerService.unregistersRunner(nickname);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    @RequestMapping(value = "/runner/{nickname}/run", method = RequestMethod.POST)
    public ResponseEntity<Object> insertRun(@PathVariable String nickname, @RequestBody RunResource run) {

        try {
            runnerService.insertRun(nickname, run.getIsoDate(), run.getDistanceKilometers(), run.getDurationInMinutes());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    @RequestMapping(value = "/runner/{nickname}/run", method = RequestMethod.GET)
    public Collection<RunResource> runs(@PathVariable String nickname) {
        Collection<Run> runs;
        try {
            runs = runnerService.getRuns(nickname);
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }

        Collection<RunResource> result = runs.stream().map(run -> resource(run)).collect(toList());
        return result;
    }

    private RunResource resource(Run run) {
        double kilometers = run.distance().in(DistanceUnit.KILOMETER);
        int minutes = (int) run.duration().get(ChronoUnit.MINUTES);
        String date = run.date().format(ISO_DATE);

        return new RunResource(kilometers, minutes, date);
    }
}
