package org.progmatic.messenger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GercikeErrorController {

    @GetMapping(value = "errors")
    public String handleErrors(HttpServletRequest request) {
        int statusCode = response.getStatus();
        String redirectUrl = "https://http.cat/" + statusCode;
        return "redirect:" + redirectUrl;
    }
}