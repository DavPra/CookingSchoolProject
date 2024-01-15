package Cooking.School.Project.cookingSchool.restapi.dto;

import Cooking.School.Project.cookingSchool.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {


    private Long userId;
    private String firstname;
    private String lastname;
    private String address;
    private String mobile;
    private String email;
    private String username;
    private boolean isAdmin;
    private Long finishedCourses;


    public static UserResponse fromUser(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setAddress(user.getAddress());
        userResponse.setMobile(user.getMobile());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setAdmin(user.isAdmin());
        userResponse.setFinishedCourses(user.getFinishedCourses());
        return userResponse;
    }
}


