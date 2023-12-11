package Cooking.School.Project.cookingSchool.restapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO1 {

    private Long userID;

    private String name;

    private String email;

    private String address;
}
