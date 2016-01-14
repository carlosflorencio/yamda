package isel.pdm.yamda.data.exception;

import java.io.IOException;

/**
 * A class that represents an exception that occur when something odd happens getting
 * the data from the repository
 */
public class FailedGettingDataException extends Exception {

    /**
     * Construct the exception by passing an IOException
     * @param ex
     */
    public FailedGettingDataException(IOException ex) {
        super(ex.getMessage());
    }

    public FailedGettingDataException(String detailMessage) {
        super(detailMessage);
    }
}
