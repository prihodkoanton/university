package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.services.impl.SecurityServiceImpl;
import com.foxminded.aprihodko.task10.services.impl.UserServiceImpl;
import com.foxminded.aprihodko.task10.validator.UserValidator;

@Controller
@RequestMapping("/")
public class RootController {

    private UserServiceImpl userServiceImpl;
    private UserValidator userValidator;
    private SecurityServiceImpl securityServiceImpl;

    public RootController(UserServiceImpl userService, UserValidator userValidator,
            SecurityServiceImpl securityService) {
        this.userServiceImpl = userService;
        this.userValidator = userValidator;
        this.securityServiceImpl = securityService;
    }

//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Username or password is incorrect.");
//        }
//        if (logout != null) {
//            model.addAttribute("message", "Logged out successfully.");
//        }
//        return "login";
//    }

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
        userServiceImpl.save(userReg);
        securityServiceImpl.autoLogin(userReg.getName(), userReg.getPasswordHash());
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
