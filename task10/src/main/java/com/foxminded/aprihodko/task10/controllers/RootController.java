package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import com.foxminded.aprihodko.task10.services.SecurityService;
import com.foxminded.aprihodko.task10.services.UserService;
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

    private UserService userServiceImpl;
    private UserValidator userValidator;
    private SecurityService securityServiceImpl;

    public RootController(UserService userService, UserValidator userValidator,
            SecurityService securityService) {
        this.userServiceImpl = userService;
        this.userValidator = userValidator;
        this.securityServiceImpl = securityService;
    }

//    @PostMapping("registration")
//    String postForm(Model model, UserForm form) throws SQLException {
//        if ("reload".equals(form.getStatus())) {
//            form.setStatus("");
//            model.addAttribute(form);
//            return "add-user";
//        } else {
//            User user;
//            switch (form.getUserType()) {
//            case "USER":
//                user = new User(form.getName(), form.getRole(), form.getPasswordHash());
//                break;
//            case "STUDENT":
//                user = new Student(form.getName(), form.getGroupId(), form.getPasswordHash());
//                break;
//            case "TEACHER":
//                user = new Teacher(form.getName(), form.getCourseId(), form.getPasswordHash());
//                break;
//            default:
//                throw new IllegalStateException("Unexpected user type: " + form.getUserType());
//            }
//            userServiceImpl.save(user);
//            return "redirect:/home";
//        }
//    }
//
//    @GetMapping("registration")
//    public String showFormForAll(Model model) throws SQLException {
//        model.addAttribute("teacher", new Teacher());
//        model.addAttribute("student", new Student());
//        model.addAttribute("userForm", new UserForm());
//        return "add-user";
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

    @GetMapping({ "/", "/home" })
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
