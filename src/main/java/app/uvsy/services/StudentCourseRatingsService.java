package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.StudentCourseRating;
import app.uvsy.model.Tag;
import app.uvsy.services.exceptions.RecordNotFoundException;
import app.uvsy.validators.CourseRatingValidator;

import java.util.List;

public class StudentCourseRatingsService {

    private final CourseRatingValidator validator;

    public StudentCourseRatingsService() {
        validator = new CourseRatingValidator();
    }


    public StudentCourseRating getCourseRating(String userId, String courseId) {
        DynamoDBDAO<StudentCourseRating> courseRatingDao = DynamoDBDAO.createFor(StudentCourseRating.class);
        return courseRatingDao.get(userId, courseId)
                .orElseThrow(() -> new RecordNotFoundException(courseId));
    }

    public void saveCourseRating(String userId, String courseId, int overall,
                                 int difficulty, boolean wouldTakeAgain, List<Tag> tags) {

        DynamoDBDAO<StudentCourseRating> courseRatingDao = DynamoDBDAO.createFor(StudentCourseRating.class);

        StudentCourseRating rating = new StudentCourseRating();
        rating.setUserId(userId);
        rating.setCourseId(courseId);
        rating.setOverall(overall);
        rating.setDifficulty(difficulty);
        rating.setWouldTakeAgain(wouldTakeAgain);
        rating.setTags(tags);
        validator.validate(rating);
        courseRatingDao.save(rating);
    }
}
