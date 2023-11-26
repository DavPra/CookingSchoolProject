package Cooking.School.Project.cookingSchool.restapi.inputParams;

import Cooking.School.Project.cookingSchool.entities.CourseTag;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;

public class CourseRequest {
    private String courseTitle;

    private String description;

    private String teacher;


    private LocalDateTime date;

    private int maxAttendants;

    private int price;

    private Set<CourseTag> courseTags;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMaxAttendants() {
        return maxAttendants;
    }

    public void setMaxAttendants(int maxAttendants) {
        this.maxAttendants = maxAttendants;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<CourseTag> getCourseTags() {
        return courseTags;
    }

    public void setCourseTags(Set<CourseTag> courseTags) {
        this.courseTags = courseTags;
    }
}

