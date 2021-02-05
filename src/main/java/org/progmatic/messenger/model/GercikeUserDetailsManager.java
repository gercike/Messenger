package org.progmatic.messenger.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class GercikeUserDetailsManager implements UserDetailsManager {

    @PersistenceContext
    EntityManager em;

    public GercikeUserDetailsManager() {
    }

    @Override
    @Transactional
    public void createUser(UserDetails gercikeUser) {
        if (userExists(gercikeUser.getUsername())) {
            System.out.println("user already exists");
        } else {
            em.persist(gercikeUser);
        }
    }

    @Transactional
    public GercikeUser getGercikeUserFromDB(String userName) {
        GercikeUser gercikeUser = (GercikeUser) em.createQuery("SELECT user from GercikeUser user where user.userName = :userToGet")
                .setParameter("userToGet", userName)
                .getSingleResult();
        return gercikeUser;
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
    @Transactional
    public boolean userExists(String username) {
        try {
            em.createQuery("SELECT user from GercikeUser user where user.userName = :nameToCheck")
                    .setParameter("nameToCheck", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GercikeUser gercikeUser = (GercikeUser) em.createQuery("SELECT user from GercikeUser user where user.userName = :nameToCheck")
                .setParameter("nameToCheck", username)
                .getSingleResult();
        if (gercikeUser == null) {
            System.out.println("User does not exist.");
        }
        return gercikeUser;
    }
}
