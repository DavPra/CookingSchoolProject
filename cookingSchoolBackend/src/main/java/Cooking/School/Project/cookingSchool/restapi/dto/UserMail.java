package Cooking.School.Project.cookingSchool.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserMail {

    private long userID;

    private String userMailAddress;

    private String firstName;

    private String lastName;


}
