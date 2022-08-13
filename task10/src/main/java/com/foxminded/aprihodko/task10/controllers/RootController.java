package com.foxminded.aprihodko.task10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/users")
    public String users() {
        return "redirect:/users/list";
    }

    @GetMapping("/groups")
    public String groups() {
        return "redirect:/groups/list";
    }

    @GetMapping("/courses")
    public String curses() {
        return "redirect:/courses/list";
    }

    @GetMapping("/lessons")
    public String lessons() {
        return "redirect:/lessons/list";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "redirect:/rooms/list";
    }
}
