package Cooking.School.Project.cookingSchool.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCourse {

        private static final DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private Long courseId;

        private String courseTitle;

        private String description;

        private String teacher;

        //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String startDate;

        private int maxAttendants;

        private int price;
    public LocalDateTime getStartDateAsLocalDateTime(){
        if(this.startDate == null){
            return null;
        }
        return LocalDateTime.parse(this.startDate, START_DATE_FORMAT);
    }




}
