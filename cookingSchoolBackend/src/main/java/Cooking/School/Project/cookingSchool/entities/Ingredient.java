package Cooking.School.Project.cookingSchool.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ingredient")
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(generator = "ingredientSequence")
    @GenericGenerator(
            name = "ingredientSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ingredient_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }

    )

    private Long ingredientId;

    private String title;

    private String unit;

    private int quantity;


    public Ingredient(){

    }

    public Ingredient(Long ingredientId, String title, String unit) {
        this.ingredientId = ingredientId;
        this.title = title;
        this.unit = unit;
        this.quantity = quantity;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


