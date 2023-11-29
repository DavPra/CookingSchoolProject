package Cooking.School.Project.cookingSchool.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailRequest {

    @Setter
    private String to;

    @Setter
    private String subject;

    @Setter
    private String text;

    @Setter
    private String filePath;

}
