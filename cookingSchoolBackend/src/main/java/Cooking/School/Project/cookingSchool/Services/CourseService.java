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
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseRequest;
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseTagsRecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

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

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }


    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    /**
     * Kurs inkl courseTag noch mit id und title und noch keine ausnahmen
     *
     * @param
     * @return
     */

//TODO: Coursedata kommt als null an.
    @Transactional
    public Long createCourse(CourseRequest request) {
        Course course = new Course();
        course.setCourseTitle(request.getCourseTitle());
        course.setDescription(request.getDescription());
        course.setTeacher(request.getTeacher());
        course.setStartDate(request.getStartDate());
        course.setMaxAttendants(request.getMaxAttendants());
        course.setPrice(request.getPrice());

        Set<CourseTag> courseTags = request.getCourseTags();
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
            responseList.add(response);
        }


        return responseList;
    }


    @Transactional
    public Course updateCourse(Long courseId, String title, String description, String teacher, LocalDateTime startDate,
                               int maxAttendants, int price, Set<CourseTag> courseTags)
            throws PrimaryIdNullOrEmptyException, CourseNotFoundException, InvalidStartDateException, TagNotFoundException {

        if (courseId == null) {
            throw new PrimaryIdNullOrEmptyException("Course Id is null or empty");
        }

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found" + courseId));

        LocalDateTime currentDate = LocalDateTime.now();
        if (startDate.isBefore(currentDate)) {
            throw new InvalidStartDateException("Please enter a valid start date");
        }
        for (CourseTag courseTag : courseTags) {
            CourseTag existingCourseTag = courseTagRepository.findById(courseTag.getCourseTagId())
                    .orElseThrow(() -> new TagNotFoundException("Course tag not found"));
        }
        existingCourse.setCourseTitle(title);
        existingCourse.setDescription(description);
        existingCourse.setTeacher(teacher);
        existingCourse.setStartDate(startDate);
        existingCourse.setMaxAttendants(maxAttendants);
        existingCourse.setPrice(price);
        existingCourse.setCourseTags(courseTags);

        return courseRepository.save(existingCourse);

    }


}
