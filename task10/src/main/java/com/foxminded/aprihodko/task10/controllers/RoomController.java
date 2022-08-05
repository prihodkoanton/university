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

import com.foxminded.aprihodko.task10.dao.RoomDao;
import com.foxminded.aprihodko.task10.models.Room;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping("showFrom")
    public String showRoomForm(Room room) {
        return "add-room";
    }

    @GetMapping("list")
    public String rooms(Model model) throws SQLException {
        model.addAttribute("rooms", this.roomDao.findAll());
        return "index";
    }

    @PostMapping
    public String addRoom(Room room, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-room";
        }
        this.roomDao.save(room);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        Room room = this.roomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid room id :" + id));
        model.addAttribute("room", room);
        return "update-room";
    }

    @PostMapping("update/{id}")
    public String updateRoom(@PathVariable("id") long id, Room room, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            room.setId(id);
            return "update-room";
        }
        roomDao.save(room);
        model.addAttribute("rooms", this.roomDao.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteRoom(@PathVariable("id") long id, Model model) throws IllegalArgumentException, SQLException {
        Room room = this.roomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid room id" + id));
        this.roomDao.deleteById(id);
        model.addAttribute("room", this.roomDao.findAll());
        return "index";
    }
}
