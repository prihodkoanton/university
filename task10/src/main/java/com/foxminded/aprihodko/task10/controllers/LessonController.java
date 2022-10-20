package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Lesson;
import com.foxminded.aprihodko.task10.models.UserType;
import com.foxminded.aprihodko.task10.services.CourseService;
import com.foxminded.aprihodko.task10.services.GroupService;
import com.foxminded.aprihodko.task10.services.LessonService;
import com.foxminded.aprihodko.task10.services.RoomService;
import com.foxminded.aprihodko.task10.services.UserService;

@Controller
@RequestMapping("/lessons/")
public class LessonController {

	private LessonService lessonService;
	private RoomService roomService;
	private GroupService groupService;
	private CourseService courseService;
	private UserService userService;

	public LessonController(LessonService lessonService, RoomService roomService, GroupService groupService,
			CourseService courseService, UserService userService) {
		this.lessonService = lessonService;
		this.roomService = roomService;
		this.groupService = groupService;
		this.courseService = courseService;
		this.userService = userService;
	}

	@GetMapping("showForm")
	public String showLessonForm(Lesson lesson, Model model) throws SQLException {
		model.addAttribute("rooms", this.roomService.findAll());
		model.addAttribute("groups", this.groupService.findAll());
		model.addAttribute("courses", this.courseService.findAll());
		model.addAttribute("teachers", this.userService.findAll().stream()
				.filter(e -> e.getType().equals(UserType.TEACHER)).collect(Collectors.toList()));
		model.addAttribute("lessons", this.lessonService.findAll());
		return "add-lesson";
	}

	@GetMapping("list")
	public String lessons(Model model) throws SQLException {
		model.addAttribute("lessons", this.lessonService.findAll());
		return "index-lesson";
	}

	@PostMapping("add")
	@PreAuthorize("hasAuthority('developers:write')")
	public String addLesson(@Valid Lesson lesson, BindingResult result, Model model) throws SQLException {
		if (result.hasErrors()) {
			return "add-lesson";
		}

		this.lessonService.save(lesson);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public String showUpdateForm(@PathVariable("id") long id, Model model)
			throws IllegalArgumentException, SQLException {
		Lesson lesson = this.lessonService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid lesson id : " + id));
		model.addAttribute("rooms", this.roomService.findAll());
		model.addAttribute("groups", this.groupService.findAll());
		model.addAttribute("courses", this.courseService.findAll());
		model.addAttribute("teachers", this.userService.findAll().stream()
				.filter(e -> e.getType().equals(UserType.TEACHER)).collect(Collectors.toList()));
		model.addAttribute("lesson", lesson);
		return "update-lesson";
	}

	@PostMapping("update/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String updateLesson(@PathVariable("id") long id, @Valid Lesson lesson, BindingResult result, Model model)
			throws SQLException {
		if (result.hasErrors()) {
			lesson.setId(id);
			return "update-lesson";
		}
		lessonService.save(lesson);
		model.addAttribute("lessons", this.lessonService.findAll());
		return "index-lesson";
	}

	@GetMapping("delete/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String deleteLesson(@PathVariable("id") long id, Model model) throws SQLException {
		Lesson lesson = this.lessonService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid lesson id: " + id));
		this.lessonService.deleteById(id);
		model.addAttribute("lessons", this.lessonService.findAll());
		return "index-lesson";
	}
}
