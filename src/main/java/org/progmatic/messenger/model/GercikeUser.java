package org.progmatic.messenger.model;

import org.apache.tomcat.jni.Local;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class GercikeUser implements UserDetails {
    private String userName;
    private String password;
    private String emailAddress;
    private LocalDate birthDate;
    private String dayOfRegistration;

    public GercikeUser(String userName, String password, String emailAddress, LocalDate birthDate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATEPATTERN);
        this.dayOfRegistration = now.format(formatter);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
