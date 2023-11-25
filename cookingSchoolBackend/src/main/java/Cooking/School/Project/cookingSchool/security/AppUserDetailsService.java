package Cooking.School.Project.cookingSchool.security;

import Cooking.School.Project.cookingSchool.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserRepository.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Searching for AppUser with username: {}", username);

        if(username == null || username.trim().length() == 0) {
            throw new UsernameNotFoundException("given username was null or empty");
        }

        try {
            System.out.println(username);
            logger.info("Found AppUser: {}", username);
            return this.appUserRepository.findByUsername(username);

        } catch(Throwable t) {
            throw new UsernameNotFoundException("something went wrong, sorry!");
        }
    }
}