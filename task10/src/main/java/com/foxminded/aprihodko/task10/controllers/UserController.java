package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Course;
import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.models.Student;
import com.foxminded.aprihodko.task10.models.Teacher;
import com.foxminded.aprihodko.task10.models.User;
import com.foxminded.aprihodko.task10.models.ui.UserForm;
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

    @ModelAttribute("groups")
    List<Group> groups() throws SQLException {
        return groupService.findAll();
    }

    @ModelAttribute("courses")
    List<Course> courses() throws SQLException {
        return courseService.findAll();
    }

    @ModelAttribute("users")
    List<User> users() throws SQLException {
        return userService.findAll();
    }

    @GetMapping("showForm")
    public String showFormForAll(User user, Model model) throws SQLException {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("student", new Student());
        model.addAttribute("userForm", new UserForm());
        return "add-user";
    }

    @GetMapping("list")
    public String users(Model model) throws SQLException {
        return "index-user";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        User user = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        model.addAttribute("user", user);
        model.addAttribute("courses", this.courseService.findAll());
        model.addAttribute("groups", this.groupService.findAll());
        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            model.addAttribute("teacher", teacher);
            return "update-teacher";
        } else if (user instanceof Student) {
            Student student = (Student) user;
            model.addAttribute("student", student);
            return "update-student";
        } else {
            return "update-user";
        }
    }

    public User update(@PathVariable("id") long id) throws SQLException {
        User entity = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        if (entity instanceof Student) {
            Student student = (Student) entity;
            userService.save(student);
            return student;
        } else if (entity instanceof Teacher) {
            Teacher teacher = (Teacher) entity;
            userService.save(teacher);
            return teacher;
        } else {
            userService.save(entity);
            return entity;
        }
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, User user, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.save(user);
        return "redirect:/users/list";
    }

    @PostMapping("updateTeacher/{id}")
    public String updateTeacher(@PathVariable("id") long id, Teacher teacher, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            teacher.setId(id);
            return "update-teacher";
        }
        userService.save(teacher);
        return "redirect:/users/list";
    }

    @PostMapping("updateStudent/{id}")
    public String updateStudent(@PathVariable("id") long id, Student student, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        userService.save(student);
        return "redirect:/users/list";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws SQLException {
        User user = this.userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        this.userService.deleteById(id);
        model.addAttribute("users", this.userService.findAll());
        return "index-user";
    }

    @PostMapping("add")
    String postForm(Model model, UserForm form) throws SQLException {
        if ("reload".equals(form.getStatus())) {
            form.setStatus("");
            model.addAttribute(form);
            return "add-user";
        } else {
            User user;
            switch (form.getUserType()) {
            case "USER":
                user = new User(form.getName());
                break;
            case "STUDENT":
                user = new Student(form.getName(), form.getGroupId());
                break;
            case "TEACHER":
                user = new Teacher(form.getName(), form.getCourseId());
                break;
            default:
                throw new IllegalStateException("Unexpected user type: " + form.getUserType());
            }
            userService.save(user);
            return "redirect:list";
        }
    }
}
