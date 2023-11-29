package Cooking.School.Project.cookingSchool.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name= "recipes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
        @Id()
        @GeneratedValue(generator = "recipeSequence")
        @GenericGenerator(
                name = "recipeSequence",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "recipe_sequence"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
                }
        )


        private Long recipeId;
        @Setter
        private String title;
        @Setter
        private String description;
        @Setter
        private int difficulty;
        @Setter
        private int preparation;

        @Setter
       @ManyToMany(mappedBy = "recipes")
       private Set<Course> courses;

        @Setter
       @ManyToMany(cascade = CascadeType.ALL)
       @JoinTable(
               name = "recipe_ingredient",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
         private Set<Ingredient> ingredients;

        @Setter
        @ManyToMany
         @JoinTable(
                name = "recipe_tag",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
         private Set<RecipeTag> recipeTags;

}
