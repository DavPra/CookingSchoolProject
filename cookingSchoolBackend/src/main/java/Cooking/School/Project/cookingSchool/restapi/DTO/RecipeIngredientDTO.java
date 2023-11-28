package Cooking.School.Project.cookingSchool.restapi.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecipeIngredientDTO {

    private Long recipeId;
    @Setter
    private String recipeTitle;
    @Setter
    private String description;
    @Setter
    private int difficulty;
    @Setter
    private int preparation;
    @Setter
    private Long ingredientId;
    @Setter
    private String ingredientTitle;
    @Setter
    private String unit;
    @Setter
    private int quantity;



}
