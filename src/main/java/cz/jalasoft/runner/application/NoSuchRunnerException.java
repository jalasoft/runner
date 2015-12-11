package cz.jalasoft.runner.application;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public class NoSuchRunnerException extends Exception {

    private final String nickname;

    public NoSuchRunnerException(String nickname) {
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
        return "There is no runner of nickname " + nickname();
    }
}
