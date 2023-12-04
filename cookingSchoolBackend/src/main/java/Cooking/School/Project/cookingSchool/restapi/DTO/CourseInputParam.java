package Cooking.School.Project.cookingSchool.restapi.DTO;

import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.CourseTag;

import java.time.LocalDateTime;
import java.util.Set;

public class CourseInputParam {
    private Long courseId;

    private String title;

    private String description;

    private String teacher;

    private LocalDateTime startDate;

    private int maxAttendants;

    private int price;

   private  Set<CourseTag> courseTags;

    public CourseInputParam(){

    }

    public CourseInputParam(Long courseId, String title, String description, String teacher, LocalDateTime startDate, int maxAttendants, int price, Set<CourseTag> courseTags) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.startDate = startDate;
        this.maxAttendants = maxAttendants;
        this.price = price;
        this.courseTags = courseTags;
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

    public Set<CourseTag> getCourseTags() {
        return courseTags;
    }

    public void setCourseTags(Set<CourseTag> courseTags) {
        this.courseTags = courseTags;
    }
}
