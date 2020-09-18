package app.uvsy.tasks.subject.consumer;


import app.universy.lambda.handlers.dynamo.consumers.ModifyConsumer;
import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.StudentSubjectRating;
import app.uvsy.model.SubjectRating;

public class StudentSubjectRateModifyConsumer extends ModifyConsumer<StudentSubjectRating> {


    @Override
    protected void modify(StudentSubjectRating oldItem, StudentSubjectRating newItem) {

        String subjectId = newItem.getSubjectId();
        String oldRating = Integer.toString(oldItem.getRating());
        String newRating = Integer.toString(newItem.getRating());

        DynamoDBDAO<SubjectRating> instSubjectRateDAO = DynamoDBDAO.createFor(SubjectRating.class);

        SubjectRating subjectRating = instSubjectRateDAO.get(subjectId)
                .orElse(new SubjectRating(subjectId));


        subjectRating.getRatings().computeIfPresent(oldRating, (key, val) -> val - 1);
        subjectRating.getRatings().compute(newRating, (key, val) -> val != null ? val + 1 : 1);

        instSubjectRateDAO.save(subjectRating);
    }

    @Override
    protected Class<StudentSubjectRating> itemClass() {
        return StudentSubjectRating.class;
    }
}
