package Cooking.School.Project.cookingSchool.restapi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestAPIResponse {
    String message;
    Object object;
}

