package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.*;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.UserRepository;
import Cooking.School.Project.cookingSchool.restapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

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

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found " +id));
        return UserResponse.fromUser(user);
    }

    public void deleteUserById(Long id) {
        if(id == null){
            throw new PrimaryIdNullOrEmptyException("User Id is null or empty " +id);
        }
        userRepository.deleteById(id);
    }


    @Transactional
    public User updateUser(Long userId, User updatedUser) throws PrimaryIdNullOrEmptyException{
        if (userId == null) {
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
    @Transactional
    public User editUser(Long userId, User updatedUser) throws PrimaryIdNullOrEmptyException {
        if (userId == null) {
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

    @Transactional
    public void bookCourse(Long userId, Long courseId) {

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        final Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        int maxNumberOfAttendants = course.getMaxAttendants();

        if (course.getUsers().size() < maxNumberOfAttendants) {
            course.getUsers().add(user);
            course.setMaxAttendants(course.getMaxAttendants() - 1);
            courseRepository.save(course);

            user.getCourses().add(course);
            user.setFinishedCourses(courseId);
            userRepository.save(user);

        } else {
            throw new MaxAttendantsReachedException(maxNumberOfAttendants);
        }
    }

    public User registration(User user) throws DuplicateKeyException {
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            String errorMessage = "Die E-Mail-Adresse '" + user.getEmail() + "' ist bereits registriert.";
            throw new DuplicateKeyException(errorMessage);
        } else {
            return userRepository.save(user);
        }
    }
}


