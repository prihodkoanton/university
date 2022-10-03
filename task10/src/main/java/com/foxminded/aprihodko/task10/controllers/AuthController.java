package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.ui.UserForm;
import com.foxminded.aprihodko.task10.services.impl.UserServiceImpl;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String showFormForAll(Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("student", new Student());
        model.addAttribute("userForm", new UserForm());
        return "add-user";
    }

    @PostMapping("/registration")
    String postForm(Model model, UserForm form) throws SQLException {
        if ("reload".equals(form.getStatus())) {
            form.setStatus("");
            model.addAttribute(form);
            return "add-user";
        } else {
            User user;
            switch (form.getUserType()) {
            case "USER":
                user = new User(form.getName(), form.getRole(), form.getPasswordHash());
                break;
            case "STUDENT":
                user = new Student(form.getName(), form.getGroupId(), form.getPasswordHash());
                break;
            case "TEACHER":
                user = new Teacher(form.getName(), form.getCourseId(), form.getPasswordHash());
                break;
            default:
                throw new IllegalStateException("Unexpected user type: " + form.getUserType());
            }
            userServiceImpl.save(user);
            return "redirect:/home";
        }
    }

}
