package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.exceptions.InvalidStartDateException;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.repository.CourseRepository;
import Cooking.School.Project.cookingSchool.repository.CourseTagRepository;
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseInputParam;
import Cooking.School.Project.cookingSchool.restapi.DTO.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;


    @Autowired
    CourseTagRepository courseTagRepository;

    public CourseService(){
    }

    public Course addCourse(Course course){
        courseRepository.save(course);
        return course;
    }

    public Course getCourseById(Long id){
        return courseRepository.findById(id).get();
    }

    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Course course){
        courseRepository.save(course);
        return course;
    }

    /**
     *  Kurs inkl courseTag noch mit id und title und noch keine ausnahmen
     * @param
     * @return
     */


    @Transactional
    public Long createCourse(CourseRequest request) {
        Course course = new Course();
        course.setCourseTitle(request.getCourseTitle());
        course.setDescription(request.getDescription());
        course.setTeacher(request.getTeacher());
        course.setStartDate(request.getDate());
        course.setMaxAttendants(request.getMaxAttendants());
        course.setPrice(request.getPrice());

        Set<CourseTag> courseTags = request.getCourseTags();
        course.setCourseTags(courseTags);


        Course savedCourse = courseRepository.save(course);

        return savedCourse.getCourseId();
    }


    /**
     *
     * @return
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

//TODO Tags
    public Course  updateCourse(Long courseId, String title, String description, String teacher, LocalDateTime startDate,
                                int maxAttendants, int  price, Set<CourseTag> courseTags)
            throws PrimaryIdNullOrEmptyException, CourseNotFoundException, InvalidStartDateException {

        if(courseId == null) {
            throw new PrimaryIdNullOrEmptyException("Course Id is null or empty");
        }

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found" + courseId));

        LocalDateTime currentDate = LocalDateTime.now();
        if (startDate.isBefore(currentDate)) {
            throw new InvalidStartDateException("Please enter a valid start date");
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
