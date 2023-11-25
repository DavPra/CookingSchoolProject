package Cooking.School.Project.cookingSchool.entities;


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
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }

    )

    private Long courseTagId;

    private String title;

    @ManyToMany(mappedBy = "courseTags")
    private Set<Course> courses;


    public CourseTag(){

    }

    public CourseTag(Long tagId, String title, String description) {
        this.courseTagId = tagId;
        this.title = title;
    }

    public Long getTagId() {
        return courseTagId;
    }

    public void setTagId(Long courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
