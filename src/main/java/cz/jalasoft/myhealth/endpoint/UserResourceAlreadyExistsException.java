package cz.jalasoft.myhealth.endpoint;

import cz.jalasoft.myhealth.application.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/21/15.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserResourceAlreadyExistsException extends RuntimeException {

    UserResourceAlreadyExistsException(UserAlreadyExistsException cause) {
        super(cause.getMessage());
    }
}
