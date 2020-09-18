package app.uvsy.tasks.course.consumer;

import app.universy.lambda.handlers.dynamo.consumers.ModifyConsumer;
import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.CourseRating;
import app.uvsy.model.StudentCourseRating;
import app.uvsy.model.Tag;
import app.uvsy.model.TagRating;

import java.util.Map;
import java.util.stream.Collectors;

public class CourseRatingModifyConsumer extends ModifyConsumer<StudentCourseRating> {


    @Override
    protected void modify(StudentCourseRating oldItem, StudentCourseRating newItem) {
        System.out.println(newItem);
        DynamoDBDAO<CourseRating> courseRatingDAO = DynamoDBDAO.createFor(CourseRating.class);
        CourseRating courseRate = courseRatingDAO.get(oldItem.getCourseId())
                .orElse(new CourseRating(newItem.getCourseId()));


        System.out.println(courseRate);

        updateOverallRate(courseRate, newItem, oldItem);
        updateDifficultyRate(courseRate, newItem, oldItem);
        updateWouldTakeAgain(courseRate, newItem, oldItem);
        updateTagsRate(courseRate, newItem, oldItem);

        courseRatingDAO.save(courseRate);
    }

    private void updateOverallRate(CourseRating courseRate, StudentCourseRating newItem, StudentCourseRating oldItem) {
        String newRate = Integer.toString(newItem.getOverall());
        String oldRate = Integer.toString(oldItem.getOverall());
        courseRate.getOverall().computeIfPresent(oldRate, (tag, rate) -> rate - 1);
        courseRate.getOverall().compute(newRate, (tag, rate) -> rate != null ? rate + 1 : 1);
    }


    private void updateDifficultyRate(CourseRating courseRate, StudentCourseRating newItem, StudentCourseRating oldItem) {
        String newRate = Integer.toString(newItem.getDifficulty());
        String oldRate = Integer.toString(oldItem.getDifficulty());
        courseRate.getDifficulty().computeIfPresent(oldRate, (tag, rate) -> rate - 1);
        courseRate.getDifficulty().compute(newRate, (tag, rate) -> rate != null ? rate + 1 : 1);
    }

    private void updateWouldTakeAgain(CourseRating instRate, StudentCourseRating newItem, StudentCourseRating oldItem) {
        if (newItem.isWouldTakeAgain()) {
            instRate.incrementWouldTakeAgain();
        } else {
            instRate.incrementWouldNotTakeAgain();
        }

        if (oldItem.isWouldTakeAgain()) {
            instRate.decrementWouldTakeAgain();
        } else {
            instRate.decrementWouldNotTakeAgain();
        }
    }

    private void updateTagsRate(CourseRating courseRate, StudentCourseRating newItem, StudentCourseRating oldItem) {
        Map<Tag, Integer> institutionTags = courseRate.getTagsRating()
                .stream()
                .collect(Collectors.toMap(
                        TagRating::getTag,
                        TagRating::getRating
                ));

        newItem.getTags()
                .forEach((newTag) -> institutionTags
                        .compute(newTag, (tag, rate) -> rate != null ? rate + 1 : 1)
                );

        oldItem.getTags()
                .forEach((oldTag) -> institutionTags
                        .computeIfPresent(oldTag, (tag, rate) -> rate - 1)
                );

        courseRate.setTagsRating(institutionTags.entrySet()
                .stream()
                .filter((e) -> e.getValue() > 0)
                .map((e) -> new TagRating(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
    }


    @Override
    protected Class<StudentCourseRating> itemClass() {
        return StudentCourseRating.class;
    }
}
