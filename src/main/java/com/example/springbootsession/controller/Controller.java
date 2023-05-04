package com.example.springbootsession.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/session")
public class Controller {

    private final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";

    @GetMapping("")
    public String home(Principal principal, HttpSession session) {
        incrementCount(session, HOME_VIEW_COUNT);
        return "Hello " + principal.getName();
    }

    @GetMapping("/count")
    public String count(HttpSession session) {
        return HOME_VIEW_COUNT + ": " + session.getAttribute(HOME_VIEW_COUNT);
    }

    private void incrementCount(HttpSession session, String home_view_count) {
        Object attribute = session.getAttribute(home_view_count);
        if(attribute != null && attribute instanceof Integer) {
            session.setAttribute(home_view_count, ((Integer) attribute).intValue() + 1);
        } else {
            session.setAttribute(home_view_count, 1);
        }
    }
}
