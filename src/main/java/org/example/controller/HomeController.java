package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Spring MVC with JSP!");
        return "home";
    }

    @GetMapping("/message")
    @ResponseBody
    public String getMessage() {
        return "Hello, JSON response from Spring!";
    }
}
