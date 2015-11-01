package isel.pdm.yamda.data.exception;

import java.io.IOException;

public class ApiFailedGettingDataException extends Exception {

    public ApiFailedGettingDataException(IOException ex) {
        super(ex.getMessage());
    }
}
