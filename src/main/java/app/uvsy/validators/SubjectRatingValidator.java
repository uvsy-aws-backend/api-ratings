package app.uvsy.validators;


import app.uvsy.model.StudentSubjectRating;
import app.uvsy.validators.exceptions.SubjectRatingInvalidStarsException;
import app.uvsy.validators.exceptions.SubjectRatingNotFoundException;

import java.util.Optional;

public class SubjectRatingValidator {

    private static final int UPPER_BOUND = 5;
    private static final int LOWER_BOUND = 1;

    public void validate(StudentSubjectRating studentSubjectRate) {

        Integer stars = Optional.ofNullable(studentSubjectRate)
                .map(StudentSubjectRating::getRating)
                .orElseThrow(SubjectRatingNotFoundException::new);

        if (stars < LOWER_BOUND || stars > UPPER_BOUND) {
            throw new SubjectRatingInvalidStarsException();
        }
    }
}
