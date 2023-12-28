package Cooking.School.Project.cookingSchool.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecipeDto {

    private Long recipeId;
    private String title;
    private String description;
    private int difficulty;
    private int preparation;


}
