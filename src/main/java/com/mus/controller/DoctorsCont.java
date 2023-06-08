package com.mus.controller;

import com.mus.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class DoctorsCont extends Attributes {
    @GetMapping
    public String Doctors(Model model) {
        AddAttributesDoctors(model);
        return "doctors";
    }
}
