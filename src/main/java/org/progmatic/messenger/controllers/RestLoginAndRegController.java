package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.GercikeUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest")
public class RestLoginAndRegController {
    GercikeUserDetailsManager gusdm;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RestLoginAndRegController(GercikeUserDetailsManager gusdm, PasswordEncoder passwordEncoder) {
        this.gusdm = gusdm;
        this.passwordEncoder = passwordEncoder;
    }

//    @RequestMapping(value = {"/loginPage"}, method = GET)
//    public String showLoginPage() {
//        return "loginPage";
//    }
//
//    @RequestMapping(value = {"/registrationPage"}, method = GET)
//    public String showRegistrationPage(@ModelAttribute("newGercikeUser") GercikeUser gercikeUser) {
//        return "registrationForm";
//    }
}
