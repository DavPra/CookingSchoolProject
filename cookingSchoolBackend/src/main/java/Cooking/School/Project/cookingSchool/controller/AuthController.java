package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.security.AuthRequest;
import Cooking.School.Project.cookingSchool.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
       logger.info("Authentication request for username: " + authRequest.getUsername());
        logger.info("Authentication request for password...: " + authRequest.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()));

            logger.info("Authentication successful for username: " + authRequest.getUsername());

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                logger.info("Generated Token for username: " + authRequest.getUsername());
                return token;
            } else {
                throw new UsernameNotFoundException("Invalid user request");
            }
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for username: " + authRequest.getUsername());
            throw new UsernameNotFoundException("Authentication failed");
        }
    }

}
