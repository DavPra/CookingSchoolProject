package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.restapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Cooking.School.Project.cookingSchool.email.EmailRequest;
import Cooking.School.Project.cookingSchool.Services.EmailService;
import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.services.CouponCodeService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private CouponCodeService couponCodeService;

    @PostMapping("/send-email/{id}")
    public ResponseEntity<?> sendEmail(@PathVariable long id) {
        try {
            //CouponCodeService couponCodeService = new CouponCodeService();
            //couponCodeService.couponCode();
            UserResponse userResponse = userService.getUserById(id);
            emailService.sendEmail(userResponse.getEmail(), "Buchungsbestätigung Cooking Academy", "Hey " + userResponse.getFirstname() + ",\n\n" +
                    "vielen Dank für deine Buchung bei der Cooking Academy.\n\n" +
                   // "Zum Dank für deine Buchung möchten wir dir einen Rabattcode für unseren " +
                   // "Shop schenken dieser lautet wie folgt: " + couponCodeService.couponCode() + "\n\n" +
                    " Wir freuen uns auf dich!\n\n" +
                    "Liebe Grüße,\n" +
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