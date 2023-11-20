package Cooking.School.Project.cookingSchool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class EntityNotFoundException  extends RuntimeException {
    private Long entityId;

    public EntityNotFoundException(Long entityId) {
        super("Entity not found with ID: " + entityId);
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

}
