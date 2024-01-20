package Cooking.School.Project.cookingSchool.Services;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;



    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

    public void sendEmailWithAttachment(String to, String subject, String body, String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
        }
    }

}
