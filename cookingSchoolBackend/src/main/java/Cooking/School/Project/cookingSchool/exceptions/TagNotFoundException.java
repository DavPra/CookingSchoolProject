package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "tag not found")
public class TagNotFoundException extends RuntimeException {

    private Long tagId;

    public TagNotFoundException(Long tagId) {
        super("Tag not found with Id" + tagId);
        this.tagId = tagId;
    }

    public Long getTagId(){
        return tagId;
    }
}
