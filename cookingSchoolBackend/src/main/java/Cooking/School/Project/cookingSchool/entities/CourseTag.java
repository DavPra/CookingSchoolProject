package Cooking.School.Project.cookingSchool.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

import javax.persistence.*;

@Table(name = "coursetags")
@Entity

public class CourseTag {

    @Id
    @GeneratedValue(generator = "tagSequence")
    @GenericGenerator(
            name = "tagSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tag_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "allocation_size", value = "1")
            }

    )

    private Long courseTagId;

    private String courseTagTitle;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseTags")
    private Set<Course> courses;


    public CourseTag(){

    }

    public CourseTag(Long courseTagId, String courseTagTitle, Set<Course> courses) {
        this.courseTagId = courseTagId;
        this.courseTagTitle = courseTagTitle;
        this.courses = courses;
    }

    public Long getCourseTagId() {
        return courseTagId;
    }

    public void setCourseTagId(Long courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getCourseTagTitle() {
        return courseTagTitle;
    }

    public void setCourseTagTitle(String courseTagTitle) {
        this.courseTagTitle = courseTagTitle;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
