package isel.pdm.yamda.data.exception;

import java.io.IOException;

/**
 * A class that represents an exception that occur when something odd happens getting
 * the data from the api
 */
public class ApiFailedGettingDataException extends Exception {

    /**
     * Construct the exception by passing an IOException
     * @param ex
     */
    public ApiFailedGettingDataException(IOException ex) {
        super(ex.getMessage());
    }

    public ApiFailedGettingDataException(String detailMessage) {
        super(detailMessage);
    }
}
