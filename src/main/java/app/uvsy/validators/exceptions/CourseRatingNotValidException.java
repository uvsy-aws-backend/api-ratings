package app.uvsy.validators.exceptions;


import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class CourseRatingNotValidException extends APIException {

    private static final String ERROR_MESSAGE = "Course rating is not valid.";

    public CourseRatingNotValidException() {
        this(ERROR_MESSAGE);
    }

    public CourseRatingNotValidException(String message) {
        super(message, HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
