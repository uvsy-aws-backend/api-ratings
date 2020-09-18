package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.StudentSubjectRating;
import app.uvsy.model.Tag;
import app.uvsy.services.exceptions.RecordNotFoundException;
import app.uvsy.validators.SubjectRatingValidator;

import java.util.List;

public class StudentSubjectRatingsService {

    private final SubjectRatingValidator validator;

    public StudentSubjectRatingsService() {
        validator = new SubjectRatingValidator();
    }


    public StudentSubjectRating getSubjectRating(String userId, String subjectId) {
        DynamoDBDAO<StudentSubjectRating> subjectRatingDao = DynamoDBDAO.createFor(StudentSubjectRating.class);
        return subjectRatingDao.get(userId, subjectId)
                .orElseThrow(() -> new RecordNotFoundException(subjectId));
    }

    public void saveSubjectRating(String userId, String subjectId, int rating) {

        DynamoDBDAO<StudentSubjectRating> subjectRatingDao = DynamoDBDAO.createFor(StudentSubjectRating.class);

        StudentSubjectRating subjectRating = new StudentSubjectRating();
        subjectRating.setUserId(userId);
        subjectRating.setSubjectId(subjectId);
        subjectRating.setRating(rating);
        validator.validate(subjectRating);
        subjectRatingDao.save(subjectRating);
    }
}
