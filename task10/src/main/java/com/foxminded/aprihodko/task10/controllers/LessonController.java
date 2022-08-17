package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.dao.CourseDao;
import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.dao.LessonDao;
import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.dao.UserDao;
import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.models.UserType;

@Controller
@RequestMapping("/lessons/")
public class LessonController {

    private LessonDao lessonDao;
    private RoomDao roomDao;
    private GroupDao groupDao;
    private CourseDao courseDao;
    private UserDao userDao;

    public LessonController(LessonDao lessonDao, RoomDao roomDao, GroupDao groupDao, CourseDao courseDao,
            UserDao userDao) {
        this.lessonDao = lessonDao;
        this.roomDao = roomDao;
        this.groupDao = groupDao;
        this.courseDao = courseDao;
        this.userDao = userDao;
    }

    @GetMapping("showForm")
    public String showLessonForm(Lesson lesson, Model model) throws SQLException {
        model.addAttribute("rooms", this.roomDao.findAll());
        model.addAttribute("groups", this.groupDao.findAll());
        model.addAttribute("courses", this.courseDao.findAll());
        model.addAttribute("teachers", this.userDao.findAll().stream().filter(e -> e.getType().equals(UserType.TEACHER))
                .collect(Collectors.toList()));
        model.addAttribute("lessons", this.lessonDao.findAll());
        return "add-lesson";
    }

    @GetMapping("list")
    public String lessons(Model model) throws SQLException {
        model.addAttribute("lessons", this.lessonDao.findAll());
        return "index-lesson";
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
        model.addAttribute("rooms", this.roomDao.findAll());
        model.addAttribute("groups", this.groupDao.findAll());
        model.addAttribute("courses", this.courseDao.findAll());
        model.addAttribute("teachers", this.userDao.findAll().stream().filter(e -> e.getType().equals(UserType.TEACHER))
                .collect(Collectors.toList()));
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
        model.addAttribute("lessons", this.lessonDao.findAll());
        return "index-lesson";
    }

    @GetMapping("delete/{id}")
    public String deleteLesson(@PathVariable("id") long id, Model model) throws SQLException {
        Lesson lesson = this.lessonDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson id: " + id));
        this.lessonDao.deleteById(id);
        model.addAttribute("lessons", this.lessonDao.findAll());
        return "index-lesson";
    }
}
