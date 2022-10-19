package com.foxminded.aprihodko.task10.controllers;

import java.sql.SQLException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxminded.aprihodko.task10.models.Group;
import com.foxminded.aprihodko.task10.services.GroupService;

@Controller
@RequestMapping("/groups/")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("showForm")
    public String showGroupForm(Group group) {
        return "add-group";
    }

    @GetMapping("list")
    public String groups(Model model) throws SQLException {
        model.addAttribute("groups", this.groupService.findAll());
        return "index-group";
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String addGroup(Group group, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            return "add-group";
        }
        this.groupService.save(group);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
            throws IllegalArgumentException, SQLException {
        Group group = this.groupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group id : " + id));
        model.addAttribute("group", group);
        return "update-group";
    }

    @PostMapping("update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateGroup(@PathVariable("id") long id, Group group, BindingResult result, Model model)
            throws SQLException {
        if (result.hasErrors()) {
            group.setId(id);
            return "update-group";
        }
        groupService.save(group);
        model.addAttribute("groups", this.groupService.findAll());
        return "index-group";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deleteGroup(@PathVariable("id") long id, Model model) throws SQLException {
        Group group = this.groupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group id: " + id));
        groupService.deleteById(id);
        model.addAttribute("groups", this.groupService.findAll());
        return "index-group";
    }
}
