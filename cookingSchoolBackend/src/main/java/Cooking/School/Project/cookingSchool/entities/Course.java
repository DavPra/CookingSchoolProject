package Cooking.School.Project.cookingSchool.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "courses")
@Entity
public class Course {
   @Id
    @GeneratedValue(generator = "courseSequence")
    @GenericGenerator(
            name = "courseSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "course_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }

    )

    private Long courseId;

   private String title;

   private String description;

   private String teacher;


   private LocalDateTime date;

    private int maxAttendants;

    private int price;

   @ManyToMany(mappedBy = "courses")
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_recipe",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;



    public Course(){

   }

    public Course(Long courseId, String title, String description, String teacher, LocalDateTime date, int maxAttendants, int price, Set<User> users, Set<Recipe> recipes) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
        this.maxAttendants = maxAttendants;
        this.price = price;
        this.users = users;
        this.recipes = recipes;
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

    public int getMaxAttendants() {
        return maxAttendants;
    }

    public void setMaxAttendants(int maxAttendants) {
        this.maxAttendants = maxAttendants;
    }


}
