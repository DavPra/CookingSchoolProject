package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.EntityNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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


    @Transactional
    public User updateUser(Long userId, User updatedUser) throws PrimaryIdNullOrEmptyException, UserNotFoundException {
        if ( userId == null){
            throw new PrimaryIdNullOrEmptyException("User Id is null or empty");
        }
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setAdmin(updatedUser.isAdmin());

        userRepository.save(existingUser);

        return existingUser;


    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void bookCourse(Long userId, Long courseId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (user != null && course != null) {
            userRepository.save(user);
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
}


