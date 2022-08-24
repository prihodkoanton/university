package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.CourseService;
import com.foxminded.aprihodko.task10.services.GroupService;
import com.foxminded.aprihodko.task10.services.UserService;

@Controller
@RequestMapping("/users/")
public class UserController {

    private UserService userService;
    private CourseService courseService;
    private GroupService groupService;

    public UserController(UserService userService, CourseService courseService, GroupService groupService) {
        this.userService = userService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @GetMapping("showForm")
    public String showFormForAll(User user, Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("student", new Student());
        model.addAttribute("groups", this.groupService.findAll());
        model.addAttribute("courses", this.courseService.findAll());
        return "add-user-new";
    }

    @GetMapping("add-student")
    public String showFormForStudent(User user, Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("student", new Student());
        model.addAttribute("groups", this.groupService.findAll());
        model.addAttribute("courses", this.courseService.findAll());
        return "add-student";
    }

    @GetMapping("add-teacher")
    public String showFormForTeacher(User user, Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("courses", this.courseService.findAll());
        return "add-teacher";
    }

    @GetMapping("add-none")
    public String showFormForNone(User user, Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("student", new Student());
        model.addAttribute("groups", this.groupService.findAll());
        model.addAttribute("courses", this.courseService.findAll());
        return "add-none";
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
        model.addAttribute("user", user);
        this.userService.save(user);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        User user = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        Teacher teacher = (Teacher) this.userService.findAll().stream()
                .filter(e -> e.getType().equals(UserType.TEACHER)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        Student student = (Student) this.userService.findAll().stream()
                .filter(e -> e.getType().equals(UserType.STUDENT)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        model.addAttribute("teacher", teacher);
        model.addAttribute("student", student);
        model.addAttribute("user", user);
        model.addAttribute("courses", this.courseService.findAll());
        model.addAttribute("groups", this.courseService.findAll());
        if (user.getType().equals(UserType.STUDENT)) {
            return "update-student";
        } else if (user.getType().equals(UserType.TEACHER)) {
            return "update-teacher";
        }
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, User user, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        model.addAttribute("users", this.userService.findAll());
        userService.save(user);

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
