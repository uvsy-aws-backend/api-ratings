package app.uvsy.model.query;

import app.uvsy.model.SubjectRating;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectRatingQueryResult {

    private final double rating;
    private final List<SubjectRating> subjectRatings;

    public SubjectRatingQueryResult(double rating, List<SubjectRating> subjectRatings) {
        this.rating = rating;
        this.subjectRatings = subjectRatings;
    }
}
