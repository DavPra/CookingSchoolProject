package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Cooking.School.Project.cookingSchool.email.EmailRequest;
import Cooking.School.Project.cookingSchool.Services.EmailService;
import Cooking.School.Project.cookingSchool.Services.UserService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/send-email/{id}")
    public ResponseEntity<?> sendEmail(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);
            emailService.sendEmail(user.getEmail(), "Buchungsbestätigung Cooking Academy", "Hey " + user.getFirstname() + ",\n\n" +
                    "vielen Dank für deine Buchung bei der Cooking Academy. Wir freuen uns auf dich!\n\n" +
                    "Viele Grüße,\n" +
                    "dein Cooking Academy Team");
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