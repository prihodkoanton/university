package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.services.UserService;

@Controller
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("showForm")
    public String showForm(User user) {
        return "add-user";
    }

    @GetMapping("list")
    public String users(Model model) throws SQLException {
        model.addAttribute("users", this.userService.findAll());
        return "index-user";
    }

    @PostMapping("add")
    public String addUser(User user, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-user";
        }
        this.userService.save(user);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        User user = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, User user, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.save(user);
        model.addAttribute("users", this.userService.findAll());
        return "index-user";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws SQLException {
        User user = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        this.userService.deleteById(id);
        model.addAttribute("users", this.userService.findAll());
        return "index-user";
    }
}
