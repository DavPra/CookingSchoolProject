package Cooking.School.Project.cookingSchool.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name= "recipes")

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
        private String title;
        private String description;
        private int difficulty;

        private int preparation;

       @ManyToMany(mappedBy = "recipe")
       private Set<Course> courses;

       @ManyToMany(mappedBy = "recipe")
       @JoinTable(
               name = "recipe_ingredient",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
         private Set<Ingredient> ingredients;

       @ManyToMany(mappedBy = "recipe")
         @JoinTable(
                name = "recipe_tag",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id"))
         private Set<RecipeTag> recipeTags;


        public Recipe(){

        }

        public Recipe(Long recipeId, String title, String description, int difficulty, int preparation) {
                this.recipeId = recipeId;
                this.title = title;
                this.description = description;
                this.difficulty = difficulty;
                this.preparation = preparation;

        }

        public Long getRecipeId() {
                return recipeId;
        }

        public void setRecipeId(Long recipeId) {
                this.recipeId = recipeId;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getDifficulty() {
                return difficulty;
        }

        public void setDifficulty(int difficulty) {
                this.difficulty = difficulty;
        }

        public int getPreparation() {
                return preparation;
        }

        public void setPreparation(int preparation) {
                this.preparation = preparation;
        }
}
