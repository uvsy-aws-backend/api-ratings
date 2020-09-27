package app.uvsy.controllers.subjects;

import app.uvsy.controllers.subjects.payload.SubjectQueryPayload;
import app.uvsy.model.SubjectRating;
import app.uvsy.model.query.SubjectRatingQueryResult;
import app.uvsy.response.Response;
import app.uvsy.services.SubjectRatingsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.Optional;

public class SubjectRatingController {

    private final SubjectRatingsService subjectRatingsService;

    public SubjectRatingController(SubjectRatingsService subjectRatingsService) {
        this.subjectRatingsService = subjectRatingsService;
    }

    public SubjectRatingController() {
        this(new SubjectRatingsService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/ratings/subjects/{id}")
    public Response<SubjectRating> getSubjectRating(@PathParameter(name = "id") String subjectId) {
        return Response.of(subjectRatingsService.getSubjectRating(subjectId));
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/ratings/subjects/query")
    public Response<SubjectRatingQueryResult> getSubjectsRating(@QueryParameter(name = "onlyRating", required = false) Boolean onlyRating,
                                                                @BodyParameter SubjectQueryPayload payload) {
        return Response.of(subjectRatingsService.resolveSubjectsQuery(
                payload.getSubjectsId(),
                Optional.ofNullable(onlyRating).orElse(Boolean.FALSE)
        ));
    }

}
