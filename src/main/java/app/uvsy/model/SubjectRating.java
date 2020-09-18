package app.uvsy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@DynamoDBTable(tableName = "subjects-rating")
public class SubjectRating {

    @DynamoDBHashKey(attributeName = "subject_id")
    private String subjectId;

    @DynamoDBAttribute(attributeName = "ratings")
    private Map<String, Integer> ratings;

    public SubjectRating() {
        this.ratings = new HashMap<>();
    }

    public SubjectRating(String subjectId) {
        this();
        this.subjectId = subjectId;
    }
}
