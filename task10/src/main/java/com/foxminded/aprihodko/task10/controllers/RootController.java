package com.foxminded.aprihodko.task10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping
    String index() {
        return "redirect:/users/list";
    }
}
