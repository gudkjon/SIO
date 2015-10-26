package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "result") // If you want to specify a table name, you can do so here
public class Result {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long surveyId;
    //private String linkText;
    private Long questionId;
    private Long optionId;
    private Long userId;
    //private String[] options;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Result() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() { return surveyId; }

    public void setSurveyId(Long surveyId) { this.surveyId = surveyId; }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getQuestionId() { return questionId; }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Result [surveyId=%d, questionId=%d, optionId=%d, userId=%d]",
                surveyId,questionId, optionId, userId);
    }
}
