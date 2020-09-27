package app.uvsy.controllers.courses.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseReportPayload {

    private final List<String> coursesId;

    public CourseReportPayload(@JsonProperty(value = "coursesId") List<String> coursesId) {
        this.coursesId = coursesId;
    }
}
