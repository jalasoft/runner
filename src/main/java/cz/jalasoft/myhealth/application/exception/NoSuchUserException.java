package cz.jalasoft.myhealth.application.exception;

import cz.jalasoft.myhealth.domain.model.user.Email;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public class NoSuchUserException extends Exception {

    private final Email email;

    public NoSuchUserException(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Email must not be null.");
        }

        this.email = new Email(emailAddress);
    }

    public Email email() {
        return email;
    }

    @Override
    public String getMessage() {
        return "There is no user of email " + email().address();
    }
}
