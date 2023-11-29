package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.restapi.DTO.DownloadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import Cooking.School.Project.cookingSchool.Services.DownloadService;

import java.io.IOException;

@Controller
public class DownloadController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @PostMapping("/download/pdf")
    @ResponseBody
    public void createPDF(@RequestBody DownloadRequest request) throws IOException {
        downloadService.createPDF(request.getContent());
    }
}
