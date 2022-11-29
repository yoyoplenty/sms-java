package com.example.SchoolManagementSystem.Auth.Security.Service;

import com.example.SchoolManagementSystem.Enum.EnumUserType;
import com.example.SchoolManagementSystem.Student.Student;
import com.example.SchoolManagementSystem.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private User user;
    private Student student;
    private UUID id;
    private String email;
    private String username;
    private String studentId;

    private EnumUserType userType;
    private Boolean enabled;
    private Boolean locked;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(UUID id, String email, String studentId, EnumUserType userType, String username, String password,
                           Boolean enabled, Boolean locked, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.studentId = studentId;
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.locked = locked;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getStudentId(),
                user.getUserType(),
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getLocked(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String getPassword() {
        return password;
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

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
