package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.SubjectRating;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SubjectRatingsService {

    public SubjectRating getSubjectRating(String subjectId) {
        DynamoDBDAO<SubjectRating> subjectRatingDao = DynamoDBDAO.createFor(SubjectRating.class);
        return subjectRatingDao.get(subjectId)
                .orElseThrow(()-> new RecordNotFoundException(subjectId));
    }

    public List<SubjectRating> getSubjectsRating(List<String> subjectsId) {
        DynamoDBDAO<SubjectRating> subjectRatingDao = DynamoDBDAO.createFor(SubjectRating.class);
        return subjectsId.stream()
                .map(subjectRatingDao::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
