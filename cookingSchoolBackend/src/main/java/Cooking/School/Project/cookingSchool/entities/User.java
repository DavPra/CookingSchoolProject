package Cooking.School.Project.cookingSchool.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "userSequence")
    @GenericGenerator(
            name = "userSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    private Long userId;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String address;

    @Setter
    private String mobile;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private boolean isAdmin;

    @Setter
    private Long finishedCourses;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set<Course> courses = new HashSet<>();

}
