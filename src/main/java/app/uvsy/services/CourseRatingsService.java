package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.CourseRating;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRatingsService {

    public CourseRating getCourseRating(String courseId) {
        DynamoDBDAO<CourseRating> courseRatingDao = DynamoDBDAO.createFor(CourseRating.class);
        return courseRatingDao.get(courseId)
                .orElseThrow(()-> new RecordNotFoundException(courseId));
    }

    public List<CourseRating> getCoursesRating(List<String> coursesId) {
        DynamoDBDAO<CourseRating> courseRatingDao = DynamoDBDAO.createFor(CourseRating.class);
        return coursesId.stream()
                .map(courseRatingDao::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
