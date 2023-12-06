package Cooking.School.Project.cookingSchool.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

import javax.persistence.*;

@Table(name = "coursetag")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

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

    @Setter
    private String courseTagTitle;

    @Setter
    @JsonIgnore
    @ManyToMany(mappedBy = "courseTags")
    private Set<Course> courses;


}
