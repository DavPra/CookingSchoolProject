package Cooking.School.Project.cookingSchool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;



    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity

    public class SecurityConfig {

        @Autowired
        private JwtFilter authFilter;

        @Bean
        public UserDetailsService userDetailsService() {
            return new AppUserDetailsService();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()

                    .antMatchers("/courses/**", "/registration/**", "/authenticate/**").permitAll() // Erlaubt den Zugriff auf /course/** und /registration/**
                   .antMatchers("/admin/**").hasAuthority("ADMIN") // Zugriff nur für Benutzer mit der Autorität "ADMIN"
                    .antMatchers("/users/**").hasAuthority("!ADMIN")
                   //.anyRequest().permitAll() // Erlaubt den Zugriff auf alle URLs
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService());
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        /**
         @PostConstruct
         public void testPasswordEncoder() {
         String rawPassword = "testpassword";
         String encodedPassword = passwordEncoder().encode(rawPassword);

         System.out.println("Raw Password: " + rawPassword);
         System.out.println("Encoded Password: " + encodedPassword);
         }

         **/

    }
