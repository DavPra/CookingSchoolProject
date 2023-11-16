package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(){
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }
}
