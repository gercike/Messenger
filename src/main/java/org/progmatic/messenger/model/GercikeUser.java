package org.progmatic.messenger.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GercikeUser implements UserDetails {
    private String userName;
    private String password;
    private String emailAddress;
    private String birthDate;
    private String dayOfRegistration;
    private String role;

    public GercikeUser(String userName, String password, String emailAddress, String birthDate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATEPATTERN);
        this.birthDate = birthDate;
        LocalDateTime now = LocalDateTime.now();
        this.dayOfRegistration = now.format(formatter);
        if (!(userName == null) && userName.equals("gercike")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDayOfRegistration() {
        return dayOfRegistration;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
}
