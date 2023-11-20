package Cooking.School.Project.cookingSchool.restapi.inputParams;

import java.time.LocalDateTime;

public class CourseInputParam {
    private Long courseId;

    private String title;

    private String description;

    private String teacher;

    private LocalDateTime date;

    public CourseInputParam(){

    }

    public CourseInputParam(Long courseId, String title, String description, String teacher, LocalDateTime date) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
