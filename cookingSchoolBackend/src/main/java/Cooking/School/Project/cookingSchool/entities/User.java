package Cooking.School.Project.cookingSchool.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "BENUTZER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "userSequence")
    @GenericGenerator(
            name = "userSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    private Long userId;

    @Setter
    private String firstname;

    @Setter
    private String lastname;

    @Setter
    private String address;

    @Setter
    private String mobile;

    @Setter
    private String email;

   // @Setter
    private String password;

    @Column(name = "USERNAME")
    @Setter
    private String username;

    @Setter
    private boolean isAdmin;

    @Setter
    private Long finishedCourses;

    @Setter
    @ManyToMany(mappedBy = "users")
    Set<GrantedAuthorityImpl> authorities;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set<Course> courses = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }*/

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }



}
