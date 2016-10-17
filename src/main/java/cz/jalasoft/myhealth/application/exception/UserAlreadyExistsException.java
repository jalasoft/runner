package cz.jalasoft.myhealth.application.exception;

import cz.jalasoft.myhealth.domain.model.user.Email;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
public class UserAlreadyExistsException extends Exception {

    private final Email email;

    public UserAlreadyExistsException(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }
        this.email = email;
    }

    public Email email() {
        return email;
    }

    @Override
    public String getMessage() {
        return "There already exists a user of email " + email().address();
    }
}
