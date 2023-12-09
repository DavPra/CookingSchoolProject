package Cooking.School.Project.cookingSchool.restapi.DTO;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTagsRecipeResponse {

    private Long courseId;

    private String courseTitle;

    private String description;

    private String teacher;

    private LocalDateTime startDate;

    private int maxAttendants;

    private int price;

    private Set<CourseTag> courseTags;

    private Set<Recipe> recipes;

    private Set<User> userIds;


    public void setCourse(Course course) {
        this.courseId = course.getCourseId();
        this.courseTitle = course.getCourseTitle();
        this.description = course.getDescription();
        this.teacher = course.getTeacher();
        this.startDate = course.getStartDate();
        this.maxAttendants = course.getMaxAttendants();
        this.price = course.getPrice();
        this.courseTags = course.getCourseTags();
        this.recipes = course.getRecipes();
        this.userIds = course.getUsers();
    }

}

