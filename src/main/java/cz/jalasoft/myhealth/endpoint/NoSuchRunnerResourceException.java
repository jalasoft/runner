package cz.jalasoft.myhealth.endpoint;

import cz.jalasoft.myhealth.application.exception.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/21/15.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchRunnerResourceException extends RuntimeException {

    public NoSuchRunnerResourceException(NoSuchUserException cause) {
        super(cause.getMessage());
    }
}
