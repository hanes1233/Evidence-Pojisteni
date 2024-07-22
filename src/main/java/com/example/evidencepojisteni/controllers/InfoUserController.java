package com.example.evidencepojisteni.controllers;

import com.example.evidencepojisteni.models.dto.InsuranceDTO;
import com.example.evidencepojisteni.models.dto.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class InfoUserController {
    @GetMapping ({"/","/index"})
    public String index() {
        return "/user/index";
    }
    @GetMapping("contact")
    public String contact() {
        return "/user/contact";
    }
    @GetMapping("info")
    public String info() {
        return "/user/info";
    }
    @GetMapping("registrace")
    public String registration(@ModelAttribute RegistrationForm registrationForm) {
        return "/user/registration";
    }
    @GetMapping("/user/profile")
    public String profile(@ModelAttribute InsuranceDTO insuranceDTO, BindingResult result) {
        return "/user/profile";
    }
}
