package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "option") // If you want to specify a table name, you can do so here
public class Option {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String containingSurvey;
    private String containingQuestion;
    private String optionText;
    //private String[] options;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainingSurvey() { return containingSurvey; }

    public void setContainingSurvey(String containingSurvey) { this.containingSurvey = containingSurvey; }

    public String getContainingQuestion() { return containingQuestion; }

    public void setContainingQuestion(String containingQuestion) {
        this.containingQuestion = containingQuestion;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }


    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "optionText [containingQuestion=%s, optionText=%s]",
                containingQuestion, optionText);
    }
}
