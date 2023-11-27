package Cooking.School.Project.cookingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Cooking.School.Project.cookingSchool.email.EmailRequest;
import Cooking.School.Project.cookingSchool.Services.EmailService;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        return "Email sent successfully!";
    }

    @PostMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText(), emailRequest.getFilePath()
                );
        return "Email with attachment sent successfully!";
    }
}