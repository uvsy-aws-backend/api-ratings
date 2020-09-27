package app.uvsy.controllers.courses.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseQueryPayload {

    private final List<String> coursesId;

    public CourseQueryPayload(@JsonProperty(value = "coursesId") List<String> coursesId) {
        this.coursesId = coursesId;
    }
}
