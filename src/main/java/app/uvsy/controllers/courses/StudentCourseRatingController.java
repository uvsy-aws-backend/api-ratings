package app.uvsy.controllers.courses;

import app.uvsy.controllers.courses.payload.CreateCourseRatingPayload;
import app.uvsy.model.StudentCourseRating;
import app.uvsy.response.Response;
import app.uvsy.services.StudentCourseRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;

public class StudentCourseRatingController {

    private final StudentCourseRatingsService courseRatingsService;

    public StudentCourseRatingController(StudentCourseRatingsService courseRatingsService) {
        this.courseRatingsService = courseRatingsService;
    }

    public StudentCourseRatingController() {
        this(new StudentCourseRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/students/{user_id}/courses/{id}")
    public Response<StudentCourseRating> getCourseRating(@PathParameter(name = "user_id") String userId,
                                                         @PathParameter(name = "id") String courseId) {
        return Response.of(courseRatingsService.getCourseRating(userId, courseId));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/ratings/students/{user_id}/courses/{id}")
    public void saveCourseRating(@PathParameter(name = "user_id") String userId,
                                 @PathParameter(name = "id") String courseId,
                                 @BodyParameter CreateCourseRatingPayload payload) {
        courseRatingsService.saveCourseRating(
                userId,
                courseId,
                payload.getOverall(),
                payload.getDifficulty(),
                payload.isWouldTakeAgain(),
                payload.getTags()
        );
    }

}
