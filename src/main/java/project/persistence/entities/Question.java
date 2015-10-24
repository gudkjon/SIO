package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "question") // If you want to specify a table name, you can do so here
public class Question {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long surveyId;
    private String questionText;
    private String type;
    //private String linkText;
    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Question() {
    }

    public Question(String questionText, String type) {
        this.questionText = questionText;
        this.type = type;
    }

    public Question(Long surveyId) {
        this.surveyId = surveyId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() { return surveyId; }

    public void setSurveyId(Long surveyId) { this.surveyId = surveyId; }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    //public String getLinkText() { return linkText; }

    //public void setLinkText(String linkText) { this.linkText = linkText; }

    //public String[] getOptions() {

    //public void setOptions(String[] options) {

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Survey [question=%s, type=%s]",
                questionText, type);
    }
}
