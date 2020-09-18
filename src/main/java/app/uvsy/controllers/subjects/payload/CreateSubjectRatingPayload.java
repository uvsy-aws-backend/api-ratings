package app.uvsy.controllers.subjects.payload;

import app.uvsy.model.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateSubjectRatingPayload {

    private final int rating;

    public CreateSubjectRatingPayload(@JsonProperty(value = "rating") int rating) {
        this.rating = rating;
    }
}
