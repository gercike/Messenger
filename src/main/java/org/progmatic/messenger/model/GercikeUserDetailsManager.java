package org.progmatic.messenger.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class GercikeUserDetailsManager implements UserDetailsManager {

    private List<UserDetails> gercikeUserList = new ArrayList<>();

    @Override
    public void createUser(UserDetails gercikeUser) {
        if (userExists(gercikeUser.getUsername())) {
            System.out.println("user already exists");
        } else {
            this.gercikeUserList.add(gercikeUser);
        }
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        for (UserDetails userDetails : gercikeUserList) {
            if (userDetails.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GercikeUser gercikeUser = null;
        for (UserDetails userDetails : gercikeUserList) {
            if (userDetails.getUsername().equals(username)) {
                gercikeUser = (GercikeUser) userDetails;
            }
        }
        return gercikeUser;
    }
}
