package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class DownloadController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @PostMapping("/download/pdf/{recipeId}")
    @ResponseBody
    public void createPDF(@PathVariable Long recipeId) throws IOException {
        downloadService.createPDF(recipeId);
    }
}
