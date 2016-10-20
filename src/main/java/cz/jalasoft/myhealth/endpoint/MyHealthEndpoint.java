package cz.jalasoft.myhealth.endpoint;

import cz.jalasoft.myhealth.application.MyHealthApplicationService;
import cz.jalasoft.myhealth.application.exception.NoSuchUserException;
import cz.jalasoft.myhealth.application.exception.UserAlreadyExistsException;
import cz.jalasoft.myhealth.domain.model.run.Run;
import cz.jalasoft.myhealth.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-29.
 */
@RestController
public class MyHealthEndpoint {

    private final MyHealthApplicationService service;

    @Autowired
    public MyHealthEndpoint(MyHealthApplicationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<UserResource> registerUser(@RequestBody UserResource resource) {
        LocalDate birthdate = LocalDate.parse(resource.getBirthday(), ISO_DATE);

        try {
            User user = service.registerUser(
                    resource.getEmailAddress(),
                    resource.getFirstName(),
                    resource.getLastName(),
                    birthdate);

            UserResource responseResource = Resources.user(user);

            responseResource.add(linkTo(methodOn(MyHealthEndpoint.class).getUser(resource.getEmailAddress())).withSelfRel());
            responseResource.add(linkTo(methodOn(MyHealthEndpoint.class).unregisterUser(resource.getEmailAddress())).withRel("unregister"));
            responseResource.add(linkTo(methodOn(MyHealthEndpoint.class).allRuns(resource.getEmailAddress())).withRel("runs"));

            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException exc) {
            throw new UserResourceAlreadyExistsException(exc);
        }
    }

    @RequestMapping(value = "/runner/{emailAddress}/", method = RequestMethod.DELETE)
    public ResponseEntity<Object> unregisterUser(@PathVariable String emailAddress) {
        try {
            service.unregistersUser(emailAddress);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchUserException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    @RequestMapping(value = "/runner/{emailAddress}")
    public UserResource getUser(@PathVariable String emailAddress) {
        try {
            User user = service.getUser(emailAddress);
            UserResource resource = Resources.user(user);

            resource.add(linkTo(methodOn(MyHealthEndpoint.class).unregisterUser(emailAddress)).withRel("unregister"));
            resource.add(linkTo(methodOn(MyHealthEndpoint.class).allRuns(emailAddress)).withRel("runs"));

            return resource;
        } catch (NoSuchUserException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

    //-------------------------------------------------------------------------------------
    //RUNS
    //-------------------------------------------------------------------------------------

    @RequestMapping(value = "/user/{emailAddress}/run", method = RequestMethod.POST)
    public ResponseEntity<Object> insertRun(@PathVariable String nickname, @RequestBody RunResource runResource) {

        try {
            Run run = service.insertRun(nickname, runResource.getIsoDate(), runResource.getDistanceKilometers(), runResource.getDurationInMinutes());
            RunResource responseResource = Resources.run(run);

            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (NoSuchUserException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }



    @RequestMapping(value = "/user/{emailAddress}/run", method = RequestMethod.GET)
    public RunsResource allRuns(@PathVariable String emailAddress) {
        Collection<Run> runs = getRuns(emailAddress);

        Collection<RunResource> runResources = runs.stream().map(run -> Resources.run(run)).collect(toList());
        RunsResource result = new RunsResource(runResources);
        result.add(linkTo(methodOn(MyHealthEndpoint.class).getUser(emailAddress)).withRel("runner"));
        result.add(linkTo(methodOn(MyHealthEndpoint.class).unregisterUser(emailAddress)).withRel("unregister"));

        return result;
    }

    private Collection<Run> getRuns(String emailAddress) {
        try {
            Collection<Run> result = service.getRuns(emailAddress);
            return result;
        } catch (NoSuchUserException exc) {
            throw new NoSuchRunnerResourceException(exc);
        }
    }

}
