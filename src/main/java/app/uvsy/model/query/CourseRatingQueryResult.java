package app.uvsy.model.query;

import app.uvsy.model.CourseRating;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseRatingQueryResult {

    private final double rating;
    private final List<CourseRating> courseRatings;

    public CourseRatingQueryResult(double rating, List<CourseRating> courseRatings) {
        this.rating = rating;
        this.courseRatings = courseRatings;
    }
}
