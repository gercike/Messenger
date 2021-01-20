package org.progmatic.messenger.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Scope("singleton")
public class LoginAndRegistrationController {
/*
    @RequestMapping(value = {"/loginPage"}, method = GET)
    public String showLoginPage() {
        return "loginPage";
    }

    @RequestMapping(value = {"/loginPage"}, method = POST)
    public String logIn(@RequestParam("username") String un,
                        @RequestParam("password") String pw,
                        Model model) {
        return "/";
    }*/
}
