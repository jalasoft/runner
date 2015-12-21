package cz.jalasoft.runner.infrastructure.endpoint;

import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/21/15.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RunnerResourceAlreadyExistsException extends RuntimeException {

    RunnerResourceAlreadyExistsException(RunnerAlreadyExistsException cause) {
        super(cause.getMessage());
    }
}
