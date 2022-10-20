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

import com.foxminded.aprihodko.task10.models.Room;
import com.foxminded.aprihodko.task10.services.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

	private RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping("showForm")
	public String showRoomForm(Room room) {
		return "add-room";
	}

	@GetMapping("list")
	public String rooms(Model model) throws SQLException {
		model.addAttribute("rooms", this.roomService.findAll());
		return "index-room";
	}

	@PostMapping("add")
	@PreAuthorize("hasAuthority('developers:write')")
	public String addRoom(@Valid Room room, BindingResult result, Model model) throws SQLException {
		if (result.hasErrors()) {
			return "add-room";
		}
		this.roomService.save(room);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	@PreAuthorize("hasAuthority('developers:read')")
	public String showUpdateForm(@PathVariable("id") long id, Model model)
			throws IllegalArgumentException, SQLException {
		Room room = this.roomService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid room id :" + id));
		model.addAttribute("room", room);
		return "update-room";
	}

	@PostMapping("update/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String updateRoom(@PathVariable("id") long id, @Valid Room room, BindingResult result, Model model)
			throws SQLException {
		if (result.hasErrors()) {
			room.setId(id);
			return "update-room";
		}
		roomService.save(room);
		model.addAttribute("rooms", this.roomService.findAll());
		return "index-room";
	}

	@GetMapping("delete/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public String deleteRoom(@PathVariable("id") long id, Model model) throws IllegalArgumentException, SQLException {
		Room room = this.roomService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid room id" + id));
		this.roomService.deleteById(room.getId());
		model.addAttribute("rooms", this.roomService.findAll());
		return "index-room";
	}
}
