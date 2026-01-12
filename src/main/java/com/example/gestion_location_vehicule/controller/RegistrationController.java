package com.example.gestion_location_vehicule.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RegistrationController {
    @GetMapping("/register-choice")
    public String showRegistrationChoice() {
        return "registration/register-choice";
    }
}
