package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.EntityNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    public UserService() {
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //Todo: check if user exists, update user and not create new one.
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


  /*  public UserRecipesDTO getUserCoursesDTO(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Set<Long> courseIds = user.getCourses().stream()
                .map(course -> course.getCourseId())
                .collect(Collectors.toSet());

        Set<Long> recipeIds = user.getCourses().stream()
                .flatMap(course -> course.getRecipes().stream())
                .map(recipe -> recipe.getRecipeId())
                .collect(Collectors.toSet());

        return new UserRecipesDTO(userId, courseIds, recipeIds);
    } */

    public void bookCourse(Long userId, Long courseId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (user != null && course != null) {

            course.getUsers().add(user);

            user.getCourses().add(course);
            userRepository.save(user);
            courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("User or Course not found");
        }
    }


    public User registration( User user ) throws DuplicateKeyException{
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new DuplicateKeyException(user.getEmail());
        } else {
            return userRepository.save(user);
        }
    }

    //Todo: does give a user back and not a recipe
    public User getRecipesByUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }


}


