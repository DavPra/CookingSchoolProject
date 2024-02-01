package Cooking.School.Project.cookingSchool.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserAuthority implements GrantedAuthority {
    private final String authority;

    public CustomUserAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
