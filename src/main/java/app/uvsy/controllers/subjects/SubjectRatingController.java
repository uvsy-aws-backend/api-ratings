package app.uvsy.controllers.subjects;

import app.uvsy.model.SubjectRating;
import app.uvsy.response.Response;
import app.uvsy.services.SubjectRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.List;

public class SubjectRatingController {

    private final SubjectRatingsService subjectRatingsService;

    public SubjectRatingController(SubjectRatingsService subjectRatingsService) {
        this.subjectRatingsService = subjectRatingsService;
    }

    public SubjectRatingController() {
        this(new SubjectRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/institution/subjects/{id}")
    public Response<SubjectRating> getSubjectRating(@PathParameter(name = "id") String subjectId) {
        return Response.of(subjectRatingsService.getSubjectRating(subjectId));
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/institution/subjects")
    public Response<List<SubjectRating>> getSubjectsRating(@QueryParameter(name = "subjectsId") List<String> subjectsId) {
        return Response.of(subjectRatingsService.getSubjectsRating(subjectsId));
    }
}
