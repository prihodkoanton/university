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

import com.foxminded.aprihodko.task10.dao.GroupDao;
import com.foxminded.aprihodko.task10.models.Group;

@Controller
@RequestMapping("/groups/")
public class GroupController {

    @Autowired
    private GroupDao groupDao;

//    public GroupController(GroupDao groupDao) {
//        this.groupDao = groupDao;
//    }

    @GetMapping("showForm")
    public String showGroupForm(Group group) {
        return "add-group";
    }

    @GetMapping("list")
    public String groups(Model model) throws SQLException {
        model.addAttribute("groups", this.groupDao.findAll());
        return "index-group";
    }

    @PostMapping("add")
    public String addGroup(Group group, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-group";
        }
        this.groupDao.save(group);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        Group group = this.groupDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group id : " + id));
        model.addAttribute("group", group);
        return "update-group";
    }

    @PostMapping("update/{id}")
    public String updateGroup(@PathVariable("id") long id, Group group, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            group.setId(id);
            return "update-group";
        }
        groupDao.save(group);
        model.addAttribute("groups", this.groupDao.findAll());
        return "index-group";
    }

    @GetMapping("delete/{id}")
    public String deleteGroup(@PathVariable("id") long id, Model model) throws SQLException {
        Group group = this.groupDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group id: " + id));
        groupDao.deleteById(id);
        model.addAttribute("groups", this.groupDao.findAll());
        return "index-group";
    }
}
