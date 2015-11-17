package project.persistence.entities;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "resultId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "questionId")
    private Question question;

    private Long userId;

    @OneToMany(mappedBy = "result",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Option> options;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Option> getOptions() { return options; }

    public void setOptions(Set<Option> options) { this.options = options; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Result [question=%s, user=%s]",
                this.getQuestion().getQuestionText(), userId);
    }
}
