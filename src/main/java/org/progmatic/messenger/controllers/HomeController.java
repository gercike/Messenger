package org.progmatic.messenger.controllers;

import org.progmatic.messenger.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"}, method = GET)
    public String home(@RequestParam(name = "name", required = false, defaultValue = "valaki") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = {"/dobokocka"}, method = GET)
    public String dice(@RequestParam(name = "sides", required = true, defaultValue = "6") int sides, Model model) {
        Random r = new Random();
        int number = r.nextInt(sides) + 1;
        model.addAttribute("number", number);
        model.addAttribute("sides", sides);
        return "diceRoll";
    }
}
