package app.uvsy.validators.exceptions;

import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class SubjectRatingNotFoundException extends APIException {

    private static final String ERROR_MESSAGE = "No student rating found.";

    public SubjectRatingNotFoundException() {
        super(ERROR_MESSAGE, HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
