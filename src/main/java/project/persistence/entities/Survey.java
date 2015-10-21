package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Postit author itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "survey") // If you want to specify a table name, you can do so here
public class Survey {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    //private Question[] questions;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public Survey() {
    }

    public Survey(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "Survey [name=%s, author=%s]",
                name,author);
    }
}
