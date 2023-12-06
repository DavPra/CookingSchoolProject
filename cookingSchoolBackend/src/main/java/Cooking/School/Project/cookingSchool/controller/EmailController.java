package Cooking.School.Project.cookingSchool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Cooking.School.Project.cookingSchool.email.EmailRequest;
import Cooking.School.Project.cookingSchool.Services.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
            return ResponseEntity.ok("Email sent successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal Server Error");
        }
    }

    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText(), emailRequest.getFilePath());
            return ResponseEntity.ok("Email sent successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal Server Error");
        }
    }
}