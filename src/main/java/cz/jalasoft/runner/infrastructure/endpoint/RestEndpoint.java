package cz.jalasoft.runner.infrastructure.endpoint;

import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.application.RunnerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
@RestController
public class RestEndpoint {

    @Autowired
    private RunnerApplicationService runnerService;

    @RequestMapping(value = "/runner/add", method = RequestMethod.POST)
    public void registerRunner(@RequestBody RunnerResource runner) {
        LocalDate birthdate = LocalDate.parse(runner.getBirthday(), DateTimeFormatter.ISO_DATE);

        try {
            runnerService.registerRunner(
                    runner.getNickname(),
                    runner.getFirstName(),
                    runner.getLastName(),
                    birthdate);

        } catch (RunnerAlreadyExistsException exc) {
            //TODO
        }
    }
}
