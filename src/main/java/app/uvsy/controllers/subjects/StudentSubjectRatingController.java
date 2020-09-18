package app.uvsy.controllers.subjects;

import app.uvsy.controllers.subjects.payload.CreateSubjectRatingPayload;
import app.uvsy.model.StudentSubjectRating;
import app.uvsy.response.Response;
import app.uvsy.services.StudentSubjectRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;

public class StudentSubjectRatingController {

    private final StudentSubjectRatingsService subjectRatingsService;

    public StudentSubjectRatingController(StudentSubjectRatingsService subjectRatingsService) {
        this.subjectRatingsService = subjectRatingsService;
    }

    public StudentSubjectRatingController() {
        this(new StudentSubjectRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/student/{user_id}/subjects/{id}")
    public Response<StudentSubjectRating> getSubjectRating(@PathParameter(name = "user_id") String userId,
                                                           @PathParameter(name = "id") String subjectId) {
        return Response.of(subjectRatingsService.getSubjectRating(userId, subjectId));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/ratings/student/{user_id}/subjects/{id}")
    public void saveSubjectRating(@PathParameter(name = "user_id") String userId,
                                  @PathParameter(name = "id") String subjectId,
                                  @BodyParameter CreateSubjectRatingPayload payload) {
        subjectRatingsService.saveSubjectRating(
                userId,
                subjectId,
                payload.getRating()
        );
    }

}
