package app.uvsy.tasks.course;

import app.universy.lambda.handlers.dynamo.DynamoDBStreamHandler;
import app.uvsy.model.StudentCourseRating;
import app.uvsy.tasks.course.consumer.CourseRatingInsertConsumer;
import app.uvsy.tasks.course.consumer.CourseRatingModifyConsumer;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.function.Consumer;

public class CourseRatingProcessor extends DynamoDBStreamHandler<StudentCourseRating> {

    @Override
    protected Consumer<? super StreamRecord> getInsertConsumer() {
        return new CourseRatingInsertConsumer();
    }

    @Override
    protected Consumer<? super StreamRecord> getModifyConsumer() {
        return new CourseRatingModifyConsumer();
    }

    @Override
    protected void configureMapper(ObjectMapper objectMapper) {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
}
