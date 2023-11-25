package Cooking.School.Project.cookingSchool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(name = "users")
 @Entity
public class User {
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

    private String firstname;

    private String lastname;

    private String address;

    private String mobile;

    private String email;

    private String password;

    private boolean isAdmin;

    private Long finishedCourses;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set<Course> courses = new HashSet<>();


    public User(){

    }

    public User(Long userId, String firstname, String lastname, String address, String mobile, String email, String password, boolean isAdmin, Long finishedCourses, Set<Course> courses) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.finishedCourses = finishedCourses;
        this.courses = courses;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Long getFinishedCourses() {
        return finishedCourses;
    }

    public void setFinishedCourses(Long finishedCourses) {
        this.finishedCourses = finishedCourses;
    }
}
