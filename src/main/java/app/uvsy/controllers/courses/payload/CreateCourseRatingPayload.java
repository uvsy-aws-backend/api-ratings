package app.uvsy.controllers.courses.payload;

import app.uvsy.model.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateCourseRatingPayload {

    private final int overall;
    private final int difficulty;
    private final boolean wouldTakeAgain;
    private final List<Tag> tags;


    public CreateCourseRatingPayload(@JsonProperty(value = "overall") int overall,
                                     @JsonProperty(value = "difficulty") int difficulty,
                                     @JsonProperty(value = "wouldTakeAgain") boolean wouldTakeAgain,
                                     @JsonProperty(value = "tags") List<Tag> tags) {
        this.overall = overall;
        this.difficulty = difficulty;
        this.wouldTakeAgain = wouldTakeAgain;
        this.tags = tags;
    }
}
