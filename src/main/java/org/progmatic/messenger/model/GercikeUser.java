package org.progmatic.messenger.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class GercikeUser implements UserDetails {

    @Id
    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String emailAddress;

    @Column
    private String birthDate;

    @Column
    private LocalDate dayOfRegistration;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "messageAuthor")
    private List<Message> messagesOfGercikeUser;


    public GercikeUser() {
        this.dayOfRegistration = LocalDate.now();
        this.role = new Role();
        role.setRoleName(Constants.ADMIN_ROLE);
    }

    public GercikeUser(String userName, String password, String emailAddress, String birthDate) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.dayOfRegistration = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constants.ADMIN_ROLE));
        return authorities;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Message> getMessagesOfGercikeUser() {
        return messagesOfGercikeUser;
    }

    public void setMessagesOfGercikeUser(List<Message> messagesOfGercikeUser) {
        this.messagesOfGercikeUser = messagesOfGercikeUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDayOfRegistration() {
        return dayOfRegistration;
    }

    public void setDayOfRegistration(LocalDate dayOfRegistration) {
        this.dayOfRegistration = dayOfRegistration;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
