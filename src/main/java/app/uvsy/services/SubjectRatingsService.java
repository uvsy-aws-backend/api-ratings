package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.SubjectRating;
import app.uvsy.model.query.SubjectRatingQueryResult;
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

    public SubjectRatingQueryResult resolveSubjectsQuery(List<String> subjectsId, boolean onlyRating) {
        DynamoDBDAO<SubjectRating> subjectRatingDao = DynamoDBDAO.createFor(SubjectRating.class);
        List<SubjectRating> subjectRatings = subjectsId.stream()
                .map(subjectRatingDao::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        double rating = subjectRatings.stream()
                .mapToDouble(SubjectRating::getRating)
                .average()
                .orElse(0);
        if (onlyRating) {
            return new SubjectRatingQueryResult(rating);
        }
        return new SubjectRatingQueryResult(rating, subjectRatings);
    }
}
