package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Course;
import com.foxminded.aprihodko.task10.services.CourseService;

@Controller
@RequestMapping("/courses/")
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("showForm")
	public String showCourseForm(Course course) {
		return "add-course";
	}

	@GetMapping("list")
	public String courses(Model model) throws SQLException {
		model.addAttribute("courses", this.courseService.findAll());
		return "index-course";
	}

	@PostMapping("add")
	@PreAuthorize("hasAuthority('developers:write')")
	public String addCourse(@Valid Course course, BindingResult result, Model model) throws SQLException {
		if (result.hasErrors()) {
			return "add-course";
		}
		this.courseService.save(course);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public String showUpdateForm(@PathVariable("id") long id, Model model)
			throws IllegalArgumentException, SQLException {
		Course course = this.courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course id : " + id));
		model.addAttribute("course", course);
		return "update-course";
	}

	@PostMapping("update/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String updateCourse(@PathVariable("id") long id, @Valid Course course, BindingResult result, Model model)
			throws SQLException {
		if (result.hasErrors()) {
			course.setId(id);
			return "update-course";
		}
		courseService.save(course);
		model.addAttribute("courses", this.courseService.findAll());
		return "index-course";
	}

	@GetMapping("delete/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String deleteCourse(@PathVariable("id") long id, Model model) throws SQLException {
		Course course = this.courseService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
		this.courseService.deleteById(course.getId());
		model.addAttribute("courses", this.courseService.findAll());
		return "index-course";
	}
}
