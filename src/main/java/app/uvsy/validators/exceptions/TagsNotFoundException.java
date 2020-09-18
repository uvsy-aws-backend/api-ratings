package app.uvsy.validators.exceptions;

import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class TagsNotFoundException extends APIException {

    private static final String ERROR_MESSAGE = "No tags list found for the rating";

    public TagsNotFoundException() {
        super(ERROR_MESSAGE, HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
