package Cooking.School.Project.cookingSchool.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {


    @Id()
    @GeneratedValue(generator = "authoritiesSequence")
    @GenericGenerator(
            name = "authoritiesSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "authorities_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Setter
    private String authority;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "authorities_appUsers",
            joinColumns = @JoinColumn(name = "authorityId"),
            inverseJoinColumns = @JoinColumn(name = "appUserId"))
    private Set<User> users;
}