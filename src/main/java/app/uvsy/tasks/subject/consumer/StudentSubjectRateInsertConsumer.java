package app.uvsy.tasks.subject.consumer;


import app.universy.lambda.handlers.dynamo.consumers.InsertConsumer;
import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.StudentSubjectRating;
import app.uvsy.model.SubjectRating;

public class StudentSubjectRateInsertConsumer extends InsertConsumer<StudentSubjectRating> {

    @Override
    protected void insert(StudentSubjectRating newItem) {

        String subjectId = newItem.getSubjectId();
        String rating = Integer.toString(newItem.getRating());

        DynamoDBDAO<SubjectRating> instSubjectRateDAO = DynamoDBDAO.createFor(SubjectRating.class);

        SubjectRating subjectRating = instSubjectRateDAO.get(subjectId)
                .orElse(new SubjectRating(subjectId));

        subjectRating.getRatings().compute(rating, (key, val) -> val != null ? val + 1 : 1);

        instSubjectRateDAO.save(subjectRating);
    }

    @Override
    protected Class<StudentSubjectRating> itemClass() {
        return StudentSubjectRating.class;
    }
}
