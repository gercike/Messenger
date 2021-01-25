package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.GercikeUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Scope("singleton")
public class LoginAndRegistrationController {
    UserDetailsService userDetailsService;

    @Autowired
    public LoginAndRegistrationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping(value = {"/loginPage"}, method = GET)
    public String showLoginPage() {
        return "loginPage";
    }

    @RequestMapping(value = {"/loginPage"}, method = POST)
    public String logIn(@RequestParam("username") String un,
                        @RequestParam("password") String pw) {
        System.out.println("ez a login post method");
        UserDetails ud = userDetailsService.loadUserByUsername(un);
        if (ud.getPassword().equals(pw)) {
            return "/";
        }
        return "loginPage";
    }

    @RequestMapping(value = {"/registrationPage"}, method = GET)
    public String showRegistrationPage(@ModelAttribute("newGercikeUser") GercikeUser gercikeUser) {
        return "registrationForm";
    }

    @RequestMapping(value = {"/registrationPage"}, method = POST)
    public String register(@ModelAttribute("newGercikeUser") GercikeUser gercikeUser) {
        GercikeUserDetailsManager gusdm = (GercikeUserDetailsManager) userDetailsService;
        if (gusdm.userExists(gercikeUser.getUsername())) {
            return "redirect:/loginPage";
        }
        gusdm.createUser(gercikeUser);
        return "redirect:/loginPage";
    }
}
