package Cooking.School.Project.cookingSchool.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails extends User implements UserDetails {
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, Set<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hier kannst du die Logik für das Ablaufdatum des Benutzerkontos implementieren
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hier kannst du die Logik für das gesperrte Benutzerkonto implementieren
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Hier kannst du die Logik für das Ablaufdatum der Anmeldeinformationen implementieren
    }

    @Override
    public boolean isEnabled() {
        return true; // Hier kannst du die Logik für deaktivierte Benutzer implementieren
    }
}
