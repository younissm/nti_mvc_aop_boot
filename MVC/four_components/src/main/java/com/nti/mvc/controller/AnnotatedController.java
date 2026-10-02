package com.nti.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("annotated")
public class AnnotatedController {
    @GetMapping
    public String annotated(Model model) {
        model.addAttribute("message", "@Controller get mapping is called.");
        return "annotated";
    }
}
