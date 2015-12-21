package cz.jalasoft.runner.infrastructure.endpoint;

import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/21/15.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchRunnerResourceException extends RuntimeException {

    public NoSuchRunnerResourceException(NoSuchRunnerException cause) {
        super(cause.getMessage());
    }
}
