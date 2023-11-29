package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
