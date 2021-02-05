package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.GercikeUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rest")
public class RestHomeController {
    UserDetailsService userDetailsService;

    @Autowired
    public RestHomeController(GercikeUserDetailsManager gercikeUserDetailsManager) {
        this.userDetailsService = gercikeUserDetailsManager;
    }

//    @RequestMapping(value = {"/", "/home"}, method = GET)
//    public String home(@RequestParam(name = "name", required = false, defaultValue = "valaki") String name, Model model) {
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("name", ud.getUsername());
//        return "greeting";
//    }
//
//    @RequestMapping(value = {"/dobokocka"}, method = GET)
//    public String dice(@RequestParam(name = "sides", required = true, defaultValue = "6") int sides, Model model) {
//        Random r = new Random();
//        int number = r.nextInt(sides) + 1;
//        model.addAttribute("number", number);
//        model.addAttribute("sides", sides);
//        GercikeUser ud = (GercikeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("name", ud.getUsername());
//        return "diceRoll";
//    }
}
