package hu.nye.progkor.exception;

/**
 * Wrong password or username exception.
 */
public class WrongPasswordOrUsername extends RuntimeException {

    public WrongPasswordOrUsername(String message) {
        super(message);
    }
}
