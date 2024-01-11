package Cooking.School.Project.cookingSchool.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {


    private Long userId;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String address;

    @Setter
    private String mobile;

    @Setter
    private String email;

    @Setter
    private String username;

    @Setter
    private boolean isAdmin;

    @Setter
    private Long finishedCourses;


}
