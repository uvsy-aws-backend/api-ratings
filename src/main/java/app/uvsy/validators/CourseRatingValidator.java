package app.uvsy.validators;

import app.uvsy.model.StudentCourseRating;
import app.uvsy.validators.exceptions.CourseRatingNotValidException;
import app.uvsy.validators.exceptions.TagsNotFoundException;

import java.util.Optional;

public class CourseRatingValidator {

    private static final int RATE_UPPER_BOUND = 5;
    private static final int RATE_LOWER_BOUND = 1;

    public void validate(StudentCourseRating studentCourseRating) {
        validateOverall(studentCourseRating);
        validateDifficulty(studentCourseRating);
        validateTags(studentCourseRating);
    }

    private void validateOverall(StudentCourseRating studentCourseRating) {
        Optional.ofNullable(studentCourseRating)
                .map(StudentCourseRating::getOverall)
                .filter(this::inValidRange)
                .orElseThrow(CourseRatingNotValidException::new);
    }

    private void validateDifficulty(StudentCourseRating studentCourseRating) {
        Optional.ofNullable(studentCourseRating)
                .map(StudentCourseRating::getDifficulty)
                .filter(this::inValidRange)
                .orElseThrow(CourseRatingNotValidException::new);
    }

    private void validateTags(StudentCourseRating studentCourseRating) {
        Optional.ofNullable(studentCourseRating)
                .map(StudentCourseRating::getTags)
                .orElseThrow(TagsNotFoundException::new);
    }


    private boolean inValidRange(int rate) {
        return RATE_LOWER_BOUND <= rate && rate <= RATE_UPPER_BOUND;
    }
}
