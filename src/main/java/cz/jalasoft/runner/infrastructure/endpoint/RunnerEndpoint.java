package cz.jalasoft.runner.infrastructure.endpoint;

import cz.jalasoft.runner.application.RunnerApplicationService;
import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.domain.model.run.DistanceUnit;
import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
@RestController
public class RunnerEndpoint {

    @Autowired
    private RunnerApplicationService runnerService;

    @RequestMapping(value = "/runner", method = RequestMethod.POST)
    public ResponseEntity<RunnerResource> registerRunner(@RequestBody RunnerResource resource) {
        LocalDate birthdate = LocalDate.parse(resource.getBirthday(), ISO_DATE);

        try {
            Runner runner = runnerService.registerRunner(
                    resource.getNickname(),
                    resource.getFirstName(),
                    resource.getLastName(),
                    birthdate);

            RunnerResource responseResource = resource(runner);

            responseResource.add(linkTo(methodOn(RunnerEndpoint.class).getRunner(resource.getNickname())).withSelfRel());
            responseResource.add(linkTo(methodOn(RunnerEndpoint.class).unregisterRunner(resource.getNickname())).withRel("unregister"));
            responseResource.add(linkTo(methodOn(RunnerEndpoint.class).runs(resource.getNickname())).withRel("runs"));

            return new ResponseEntity<>(resource(runner), HttpStatus.CREATED);
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
    public ResponseEntity<Object> insertRun(@PathVariable String nickname, @RequestBody RunResource runResource) {

        try {
            Run run = runnerService.insertRun(nickname, runResource.getIsoDate(), runResource.getDistanceKilometers(), runResource.getDurationInMinutes());
            RunResource responseResource = resource(run);

            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    @RequestMapping(value = "/runner/{nickname}/run", method = RequestMethod.GET)
    public RunsResource runs(@PathVariable String nickname) {
        Collection<Run> runs = getRuns(nickname);

        Collection<RunResource> runResources = runs.stream().map(run -> resource(run)).collect(toList());
        RunsResource result = new RunsResource(runResources);
        result.add(linkTo(methodOn(RunnerEndpoint.class).getRunner(nickname)).withRel("runner"));
        result.add(linkTo(methodOn(RunnerEndpoint.class).unregisterRunner(nickname)).withRel("unregister"));

        return result;
    }

    private Collection<Run> getRuns(String nickname) {
        try {
            Collection<Run> result = runnerService.getRuns(nickname);
            return result;
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }


    @RequestMapping(value = "/runner/{nickname}")
    public RunnerResource getRunner(@PathVariable String nickname) {
        try {
            Runner runner = runnerService.getRunner(nickname);
            RunnerResource resource = resource(runner);

            resource.add(linkTo(methodOn(RunnerEndpoint.class).unregisterRunner(nickname)).withRel("unregister"));
            resource.add(linkTo(methodOn(RunnerEndpoint.class).runs(nickname)).withRel("runs"));

            return resource;
        } catch (NoSuchRunnerException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    //-------------------------------------------------------
    //CONVERSION
    //-------------------------------------------------------

    private RunnerResource resource(Runner runner) {

        String nickname = runner.nickname();
        String firstName = runner.name().firstName();
        String lastName = runner.name().lastName();
        String birthday = runner.birthday().format(DateTimeFormatter.ISO_DATE);

        return new RunnerResource(nickname, firstName, lastName, birthday);
    }

    private RunResource resource(Run run) {
        double kilometers = run.distance().in(DistanceUnit.KILOMETER);
        int minutes = (int) run.duration().get(ChronoUnit.MINUTES);
        String date = run.date().format(ISO_DATE);

        return new RunResource(kilometers, minutes, date);
    }
}
