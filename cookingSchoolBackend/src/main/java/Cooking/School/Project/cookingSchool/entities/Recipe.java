package Cooking.School.Project.cookingSchool.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "recipe")

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

        @Setter(AccessLevel.NONE)
        private Long recipeId;

        private String title;
        private String description;
        private int difficulty;

        private int preparation;

        //Löscht rezepte ohne Kurse zu löschen
       @ManyToMany(mappedBy = "recipes", cascade = CascadeType.REMOVE)

       private Set<Course> courses;

       @ManyToMany(cascade = CascadeType.REMOVE)
       @JoinTable(
               name = "recipe_ingredient",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
         private Set<Ingredient> ingredients;

       @ManyToMany(cascade = CascadeType.REMOVE)
         @JoinTable(
                name = "recipe_tag",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
         private Set<RecipeTag> recipeTags;




}
