package Cooking.School.Project.cookingSchool.restapi;

public class RestAPIResponse {
    String message;
    Object object;

    public RestAPIResponse(String message, Object object)  {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

