package cz.jalasoft.runner.application.exception;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
public class RunnerAlreadyExistsException extends Exception {

    private final String nickname;

    public RunnerAlreadyExistsException(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            throw new IllegalArgumentException("Nickname must not be null or empty.");
        }
        this.nickname = nickname;
    }

    public String nickname() {
        return nickname;
    }

    @Override
    public String getMessage() {
        return "There already exists a runner of nickname " + nickname();
    }
}
