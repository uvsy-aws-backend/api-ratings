package app.uvsy.validators.exceptions;


import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class SubjectRatingInvalidStarsException extends APIException {

    private static final String ERROR_MESSAGE = "Rating value not valid.";

    public SubjectRatingInvalidStarsException() {
        super(ERROR_MESSAGE, HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
