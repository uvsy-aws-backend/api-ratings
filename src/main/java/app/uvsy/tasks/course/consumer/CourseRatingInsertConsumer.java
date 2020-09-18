package app.uvsy.tasks.course.consumer;

import app.universy.lambda.handlers.dynamo.consumers.InsertConsumer;
import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.CourseRating;
import app.uvsy.model.StudentCourseRating;
import app.uvsy.model.Tag;
import app.uvsy.model.TagRating;

import java.util.Map;
import java.util.stream.Collectors;

public class CourseRatingInsertConsumer extends InsertConsumer<StudentCourseRating> {

    @Override
    protected void insert(StudentCourseRating newItem) {
        DynamoDBDAO<CourseRating> courseRatingDAO = DynamoDBDAO.createFor(CourseRating.class);

        String courseId = newItem.getCourseId();

        CourseRating courseRating = courseRatingDAO.get(courseId).orElse(new CourseRating(newItem.getCourseId()));

        updateOverallRating(courseRating, newItem);
        updateDifficultyRating(courseRating, newItem);
        updateWouldTakeAgain(courseRating, newItem);
        updateTagsRating(courseRating, newItem);

        DynamoDBDAO<CourseRating> instCourseRatingDAO = DynamoDBDAO.createFor(CourseRating.class);
        instCourseRatingDAO.save(courseRating);
    }

    private void updateOverallRating(CourseRating courseRating, StudentCourseRating newItem) {
        String rating = Integer.toString(newItem.getOverall());
        courseRating.getOverall().compute(rating, (key, val) -> val != null ? val + 1 : 1);
    }


    private void updateDifficultyRating(CourseRating courseRating, StudentCourseRating newItem) {
        String Rating = Integer.toString(newItem.getDifficulty());
        courseRating.getDifficulty().compute(Rating, (key, val) -> val != null ? val + 1 : 1);
    }

    private void updateWouldTakeAgain(CourseRating instRating, StudentCourseRating studentRating) {
        if (studentRating.isWouldTakeAgain()) {
            instRating.incrementWouldTakeAgain();
        } else {
            instRating.incrementWouldNotTakeAgain();
        }
    }

    private void updateTagsRating(CourseRating courseRating, StudentCourseRating newItem) {
        Map<Tag, Integer> institutionTags = courseRating.getTagsRating()
                .stream()
                .collect(Collectors.toMap(
                        TagRating::getTag,
                        TagRating::getRating
                ));

        newItem.getTags()
                .forEach((tag) -> institutionTags
                        .compute(tag, (key, val) -> val != null ? val + 1 : 1)
                );

        courseRating.setTagsRating(institutionTags.entrySet()
                .stream()
                .map((e) -> new TagRating(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
    }

    @Override
    protected Class<StudentCourseRating> itemClass() {
        return StudentCourseRating.class;
    }
}
