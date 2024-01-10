package Cooking.School.Project.cookingSchool.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Table(name = "course")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

   @Setter
   private String courseTitle;

   @Setter
   private String description;

   @Setter
   private String teacher;

   @Setter
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime startDate;

    @Setter


    private int maxAttendants;

    //TODO BigDecimal to be used with respect to prices
    @Setter
    private int price;

    @Setter
   @ManyToMany(mappedBy = "courses")
    private Set<User> users = new HashSet<>();


    @JsonIgnore
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_recipe",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "course_tag",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "courseTag_id"))
    private Set<CourseTag> courseTags;
}
