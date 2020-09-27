package app.uvsy.controllers.subjects.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectReportPayload {

    private final List<String> subjectsId;

    public SubjectReportPayload(@JsonProperty(value = "subjectsId") List<String> subjectsId) {
        this.subjectsId = subjectsId;
    }
}
