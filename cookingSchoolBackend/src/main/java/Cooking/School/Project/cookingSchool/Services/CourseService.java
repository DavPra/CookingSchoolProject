package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.InvalidStartDateException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.exceptions.TagNotFoundException;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.CourseTagRepository;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseRequest;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseTagsRecipeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    CourseRepository courseRepository;


    @Autowired
    CourseTagRepository courseTagRepository;

    public CourseService() {
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
    }

    public Course getCourseDetails(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
    }



    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    /**
     * creates a course including course tags and saves it to the database
     *
     * @param request a JSON holding the information to create a course
     * @return the  course Id
     */


    @Transactional
    public Long createCourse(CourseRequest request) {
        Course course = new Course();
        course.setCourseTitle(request.getCourseTitle());
        course.setDescription(request.getDescription());
        course.setTeacher(request.getTeacher());
        course.setImage(request.getImage());
        course.setStartDate(request.getStartDate());
        course.setMaxAttendants(request.getMaxAttendants());
        course.setPrice(request.getPrice());

        Set<CourseTag> courseTags = request.getCourseTags();
        if (courseTags != null && !courseTags.isEmpty()) {
            for (CourseTag tag : courseTags) {
                CourseTag courseTag = courseTagRepository.findById(tag.getCourseTagId())
                        .orElseThrow(() -> new TagNotFoundException("Course tag not found" ));
            }
        }

        course.setCourseTags(courseTags);

        Course savedCourse = courseRepository.save(course);

        return savedCourse.getCourseId();
    }


    /**
     * @return
     */
    public List<CourseTagsRecipeResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseTagsRecipeResponse> responseList = new ArrayList<>();
        for (Course course : courses) {
            CourseTagsRecipeResponse response = new CourseTagsRecipeResponse();
            response.setCourse(course);
            Set<CourseTag> tags = course.getCourseTags();
            Set<Recipe> recipes = course.getRecipes();
            response.setCourseTags(tags);
            response.setRecipes(recipes);

            logger.info("Course ID: {}", response.getCourseId());
            logger.info("Course Title: {}", response.getCourseTitle());
            logger.info("Image: {}", response.getImage());

            responseList.add(response);
        }



        return responseList;
    }


    @Transactional
    public Course updateCourse(Long courseId, String title, String description, String teacher, String image, LocalDateTime startDate,
                               int maxAttendants, int price, Set<CourseTag> courseTags)
            throws PrimaryIdNullOrEmptyException, InvalidStartDateException {

        if (courseId == null) {
            throw new PrimaryIdNullOrEmptyException("Course Id is null or empty");
        }

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found " + courseId));

        LocalDateTime currentDate = LocalDateTime.now();
        if (startDate.isBefore(currentDate)) {
            throw new InvalidStartDateException("Please enter a valid start date");
        }

        if (courseTags != null) {
            for (CourseTag courseTag : courseTags) {
                CourseTag existingCourseTag = courseTagRepository.findById(courseTag.getCourseTagId())
                        .orElseThrow(() -> new TagNotFoundException("Course tag not found"));
            }
        }

        existingCourse.setCourseTitle(title);
        existingCourse.setDescription(description);
        existingCourse.setTeacher(teacher);
        existingCourse.setImage(image);
        existingCourse.setStartDate(startDate);
        existingCourse.setMaxAttendants(maxAttendants);
        existingCourse.setPrice(price);
        existingCourse.setCourseTags(courseTags);

        return courseRepository.save(existingCourse);
    }


}
