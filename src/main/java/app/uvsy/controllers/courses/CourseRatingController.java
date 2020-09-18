package app.uvsy.controllers.courses;

import app.uvsy.model.CourseRating;
import app.uvsy.response.Response;
import app.uvsy.services.CourseRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.List;

public class CourseRatingController {

    private final CourseRatingsService courseRatingsService;

    public CourseRatingController(CourseRatingsService courseRatingsService) {
        this.courseRatingsService = courseRatingsService;
    }

    public CourseRatingController() {
        this(new CourseRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/institution/courses/{id}")
    public Response<CourseRating> getCourseRating(@PathParameter(name = "id") String courseId) {
        return Response.of(courseRatingsService.getCourseRating(courseId));
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/institution/courses")
    public Response<List<CourseRating>> getCoursesRating(@QueryParameter(name = "coursesId") List<String> coursesId) {
        return Response.of(courseRatingsService.getCoursesRating(coursesId));
    }
}
