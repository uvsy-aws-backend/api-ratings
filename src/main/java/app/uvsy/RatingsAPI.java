package app.uvsy;

import app.uvsy.controllers.courses.CourseRatingController;
import app.uvsy.controllers.courses.StudentCourseRatingController;
import app.uvsy.controllers.subjects.StudentSubjectRatingController;
import app.uvsy.controllers.subjects.SubjectRatingController;
import org.github.serverless.api.ServerlessApiHandler;

import java.util.List;

public class RatingsAPI extends ServerlessApiHandler {

    @Override
    protected void registerControllers(List<Object> controllers) {
        controllers.add(new CourseRatingController());
        controllers.add(new StudentCourseRatingController());
        controllers.add(new SubjectRatingController());
        controllers.add(new StudentSubjectRatingController());
    }
}
