package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.dao.impl.CourseDaoImpl;
import com.foxminded.aprihodko.task10.models.Course;

@Controller
@RequestMapping("/courses/")
public class CourseController {

    @Autowired
    private CourseDaoImpl courseDao;

//    public CourseController(CourseDaoImpl courseDao) {
//        this.courseDao = courseDao;
//    }

    @GetMapping("showForm")
    public String showCourseForm(Course course) {
        return "add-course";
    }

    @GetMapping("list")
    public String courses(Model model) throws SQLException {
        model.addAttribute("courses", this.courseDao.findAll());
        return "index-course";
    }

    @PostMapping("add")
    public String addCourse(Course course, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-course";
        }
        this.courseDao.save(course);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        Course course = this.courseDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id : " + id));
        model.addAttribute("course", course);
        return "update-course";
    }

    @PostMapping("update/{id}")
    public String updateCourse(@PathVariable("id") long id, Course course, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            course.setId(id);
            return "update-course";
        }
        course.setId(id);
        courseDao.save(course);
        model.addAttribute("course", this.courseDao.findAll());
        return "index-course";
    }

    @GetMapping("delete/{id}")
    public String deleteCourse(@PathVariable("id") long id, Model model) throws SQLException {
        Course course = this.courseDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
        this.courseDao.deleteById(course.getId());
        model.addAttribute("course", this.courseDao.findAll());
        return "redirect:list";
    }
}
