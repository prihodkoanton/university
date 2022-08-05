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

import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.models.Lesson;

@Controller
@RequestMapping("/lessons/")
public class LessonController {

    @Autowired
    private final LessonDao lessonDao;

    public LessonController(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @GetMapping("showFrom")
    public String showLessonForm(Lesson lesson) {
        return "add-lesson";
    }

    @GetMapping("list")
    public String lessons(Model model) throws SQLException {
        model.addAttribute("lessons", this.lessonDao.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addLesson(Lesson lesson, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-lesson";
        }
        this.lessonDao.save(lesson);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        Lesson lesson = this.lessonDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson id : " + id));
        model.addAttribute("lesson", lesson);
        return "update-lesson";
    }

    @PostMapping("update/{id}")
    public String updateLesson(@PathVariable("id") long id, Lesson lesson, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            lesson.setId(id);
            return "update-lesson";
        }
        lessonDao.save(lesson);
        model.addAttribute("users", this.lessonDao.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteLesson(@PathVariable("id") long id, Model model) throws SQLException {
        Lesson lesson = this.lessonDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson id: " + id));
        this.lessonDao.deleteById(id);
        model.addAttribute("lesson", this.lessonDao.findAll());
        return "index";
    }
}
