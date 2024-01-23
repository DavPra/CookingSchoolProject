package Cooking.School.Project.cookingSchool.restapi.dto;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    //private static final DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String courseTitle;

    private String description;

    private String teacher;

    private String image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime startDate;

    private int maxAttendants;

    private int price;

    private Set<CourseTag> courseTags;

    /*public LocalDateTime getStartDateAsLocalDateTime(){
        if(this.startDate == null){
            return null;
        }
        return LocalDateTime.parse(this.startDate, START_DATE_FORMAT);
    }

    public void setCourse(Course course) {
        this.courseTitle = course.getCourseTitle();
        this.description = course.getDescription();
        this.teacher = course.getTeacher();
        this.startDate = course.getStartDate().format(START_DATE_FORMAT);
        this.maxAttendants = course.getMaxAttendants();
        this.price = course.getPrice();
    }*/


}

