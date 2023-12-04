package Cooking.School.Project.cookingSchool.restapi.DTO;

import java.time.LocalDateTime;

public class CourseInputParam {
    private Long courseId;

    private String title;

    private String description;

    private String teacher;

    private LocalDateTime startDate;

    private int maxAttendants;

    private int price;

    public CourseInputParam(){

    }

    public CourseInputParam(Long courseId, String title, String description, String teacher, LocalDateTime startDate) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.startDate = startDate;
        this.maxAttendants = maxAttendants;
        this.price = price;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
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
}
