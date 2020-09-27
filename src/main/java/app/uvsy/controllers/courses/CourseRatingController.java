package app.uvsy.controllers.courses;

import app.uvsy.controllers.courses.payload.CourseQueryPayload;
import app.uvsy.model.CourseRating;
import app.uvsy.model.query.CourseRatingQueryResult;
import app.uvsy.response.Response;
import app.uvsy.services.CourseRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.Optional;

public class CourseRatingController {

    private final CourseRatingsService courseRatingsService;

    public CourseRatingController(CourseRatingsService courseRatingsService) {
        this.courseRatingsService = courseRatingsService;
    }

    public CourseRatingController() {
        this(new CourseRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/courses/{id}")
    public Response<CourseRating> getCourseRating(@PathParameter(name = "id") String courseId) {
        return Response.of(courseRatingsService.getCourseRating(courseId));
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/ratings/courses/query")
    public Response<CourseRatingQueryResult> getCoursesRating(@QueryParameter(name = "onlyRating", required = false) Boolean onlyRating,
                                                              @BodyParameter CourseQueryPayload payload) {
        return Response.of(courseRatingsService.resolveCourseQuery(
                payload.getCoursesId(),
                Optional.ofNullable(onlyRating).orElse(Boolean.FALSE)
        ));
    }


}
