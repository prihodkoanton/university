package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.services.SecurityService;
import com.foxminded.aprihodko.task10.services.UserService;
import com.foxminded.aprihodko.task10.validator.UserValidator;

@Controller
public class RootController {

    private UserService userService;
    private UserValidator userValidator;
    private SecurityService securityService;

    public RootController(UserService userService, UserValidator userValidator, SecurityService securityService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userReg", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userReg") User userReg, BindingResult bindingResult, Model model)
            throws SQLException {
        userValidator.validate(userReg, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userReg);
        securityService.autoLogin(userReg.getName(), userReg.getPasswordHash());
        return "redirect:/home";
    }

    @GetMapping("/home")
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
