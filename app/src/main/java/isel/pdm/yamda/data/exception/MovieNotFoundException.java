package isel.pdm.yamda.data.exception;

/**
 * Exception throw by the application when a Movie search can't return a valid result.
 */
public class MovieNotFoundException extends Exception {

    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(final String message) {
        super(message);
    }

    public MovieNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(final Throwable cause) {
        super(cause);
    }
}
