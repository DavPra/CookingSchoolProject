package Cooking.School.Project.cookingSchool.mapstruct.dto;

import Cooking.School.Project.cookingSchool.entities.CourseTag;
import Cooking.School.Project.cookingSchool.entities.Recipe;
import Cooking.School.Project.cookingSchool.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasicCourseDTO {

    private Long courseId;


    private String courseTitle;


    private String description;


    private String teacher;


    private String image;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime startDate;


    private int maxAttendants;

    private int price;


    private Set<User> users = new HashSet<>();

    private Set<Recipe> recipes;

    private Set<CourseTag> courseTags;
}
