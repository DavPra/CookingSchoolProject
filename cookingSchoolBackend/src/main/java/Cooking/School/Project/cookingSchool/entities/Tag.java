package Cooking.School.Project.cookingSchool.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Table(name = "tags")
@Entity
public class Tag {

    @Id
    @GeneratedValue(generator = "tagSequence")
    @GenericGenerator(
            name = "tagSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tag_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }

    )

    private Long tagId;

    private String title;

    //TODO: ManyToMany mit Recipe
    private Set<Recipe> recipes = new HashSet<>();

    public Tag(){

    }

    public Tag(Long tagId, String title, String description) {
        this.tagId = tagId;
        this.title = title;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long ingredientId) {
        this.tagId = ingredientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
