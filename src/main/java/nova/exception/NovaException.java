package nova.exception;

/**
 * Represents an exception specific to the Nova application.
 */
public class NovaException extends RuntimeException {
    public NovaException(String message) {
        super(message);
    }
}
