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
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    public UserService() {
    }

/*
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }*/
    /* war neu
    public User addUser(User user){
        Optional<User> existingUserByEmail = userRepository.findUserByEmail(user.getEmail());
        Optional<User> existingUserByUsername = userRepository.findUserByUsername(user.getUsername());

        if (existingUserByEmail.isPresent()) {
            String errorMessage = "Die E-Mail-Adresse '" + user.getEmail() + "' ist bereits registriert.";
            throw new DuplicateKeyException(errorMessage);
        } else if (existingUserByUsername.isPresent()) {
            String errorMessage = "Der Benutzername '" + user.getUsername() + "' ist bereits registriert.";
            throw new DuplicateKeyException(errorMessage);
        } else {
            return userRepository.save(user);
        }
    }*/

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found " +id));
        return UserResponse.fromUser(user);
    }
    /*
    public BasicUserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found " + id));
        return UserMapper.INSTANCE.toUserResponse(user);
    }*/
    public void deleteUserById(Long id) {
        if (id == null) {
            throw new PrimaryIdNullOrEmptyException("User Id is null or empty " + id);
        }
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
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
        existingUser.setMobile(updatedUser.getMobile());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setAdmin(updatedUser.isAdmin());

        userRepository.save(existingUser);

        return existingUser;

    }


    /**
     * Get Method to get a List of all Users
     *
     * @return
     */

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoSuchElementException("No users found");
        }
        return users;
    }

    /**
     * book a course for a user and handle number of participants and save it in the database
     * @param userId
     * @param courseId
     * @throws CourseNotFoundException
     * @throws UserNotFoundException
     * @throws MaxAttendantsReachedException when course is fully booked
     */
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


    /**
     * register a new user
     * @param user
     * @return DuplicateKeyException If the email address or username is already registered.
     */
    public User registration(User user){
        Optional<User> existingUserByEmail = userRepository.findUserByEmail(user.getEmail());
       Optional<User> existingUserByUsername = userRepository.findUserByUsername(user.getUsername());

        if (existingUserByEmail.isPresent()) {
            String errorMessage = "Die E-Mail-Adresse '" + user.getEmail() + "' ist bereits registriert.";
            throw new DuplicateKeyException(errorMessage);
        } else if (existingUserByUsername.isPresent()) {
            String errorMessage = "Der Benutzername '" + user.getUsername() + "' ist bereits registriert.";
            throw new DuplicateKeyException(errorMessage);
        } else {
            return userRepository.save(user);
        }
    }

}


