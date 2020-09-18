package app.uvsy.tasks.subject;

import app.universy.lambda.handlers.dynamo.DynamoDBStreamHandler;
import app.uvsy.model.StudentSubjectRating;
import app.uvsy.tasks.subject.consumer.StudentSubjectRateInsertConsumer;
import app.uvsy.tasks.subject.consumer.StudentSubjectRateModifyConsumer;
import com.amazonaws.services.dynamodbv2.model.StreamRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.function.Consumer;

public class SubjectRatingProcessor extends DynamoDBStreamHandler<StudentSubjectRating> {

    @Override
    protected Consumer<? super StreamRecord> getInsertConsumer() {
        return new StudentSubjectRateInsertConsumer();
    }

    @Override
    protected Consumer<? super StreamRecord> getModifyConsumer() {
        return new StudentSubjectRateModifyConsumer();
    }

    @Override
    protected void configureMapper(ObjectMapper objectMapper) {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
}
