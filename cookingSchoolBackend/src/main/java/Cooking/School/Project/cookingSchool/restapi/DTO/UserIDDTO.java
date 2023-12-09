package Cooking.School.Project.cookingSchool.restapi.DTO;

import Cooking.School.Project.cookingSchool.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserIDDTO {

    private Long userId = getUserId();
}
