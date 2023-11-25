package Cooking.School.Project.cookingSchool.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "recipe_tags")
public class RecipeTag {

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

        @ManyToMany(mappedBy = "recipeTags")
        private Set<Recipe> recipes;

        public RecipeTag(){

        }

        public RecipeTag(Long tagId, String title) {
            this.tagId = tagId;
            this.title = title;
        }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
