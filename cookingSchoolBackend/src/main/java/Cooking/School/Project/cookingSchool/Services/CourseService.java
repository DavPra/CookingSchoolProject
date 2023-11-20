package Cooking.School.Project.cookingSchool.Services;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.exceptions.PrimaryIdNullOrEmptyException;
import Cooking.School.Project.cookingSchool.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CoursesRepository coursesRepository;

    public CourseService(){
    }

    public Course addCourse(Course course){
        coursesRepository.save(course);
        return course;
    }

    public Course getCourseById(Long id){
        return coursesRepository.findById(id).get();
    }

    public void deleteCourseById(Long id){
        coursesRepository.deleteById(id);
    }

    public Course updateCourse(Course course){
        coursesRepository.save(course);
        return course;
    }

    public boolean createCourse(Long courseId, String title, String description, String teacher, LocalDateTime date) {


         Course course = new Course();
            course.setTitle(title);
            course.setDescription(description);
            course. setTeacher(teacher);
            course.setDate(date);

            coursesRepository.save(course);
            return true;

    }

    public List<Course> getAllCourses(){
        return coursesRepository.findAll();
    }

}
