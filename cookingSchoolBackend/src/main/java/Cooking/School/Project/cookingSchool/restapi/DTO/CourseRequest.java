package Cooking.School.Project.cookingSchool.restapi.DTO;

import Cooking.School.Project.cookingSchool.entities.CourseTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseTitle;

    private String description;

    private String teacher;

    private LocalDateTime startDate;

    private int maxAttendants;

    private int price;

    private Set<CourseTag> courseTags;


}

