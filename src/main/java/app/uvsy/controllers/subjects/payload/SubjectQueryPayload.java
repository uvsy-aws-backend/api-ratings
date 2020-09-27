package app.uvsy.controllers.subjects.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectQueryPayload {

    private final List<String> subjectsId;

    public SubjectQueryPayload(@JsonProperty(value = "subjectsId") List<String> subjectsId) {
        this.subjectsId = subjectsId;
    }
}
