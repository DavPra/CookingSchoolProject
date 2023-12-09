package Cooking.School.Project.cookingSchool.restapi.DTO;

public class DownloadRequest {
    private String content;

    private Long id;

    // Getters and setters
    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }



    public void setContent(String content) {
        this.content = content;
    }
}

