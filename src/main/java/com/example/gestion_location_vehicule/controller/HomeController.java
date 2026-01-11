package com.example.gestion_location_vehicule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "AutoPartage - Location de véhicules");
        model.addAttribute("popularCities", List.of("Paris", "Lyon", "Marseille", "Bordeaux", "Lille"));

        // 创建车辆类型和图标对应的列表
        List<Map<String, String>> vehicleTypesWithIcons = List.of(
                Map.of("name", "Voiture", "icon", "fa-car"),
                Map.of("name", "Moto", "icon", "fa-motorcycle"),
                Map.of("name", "Camion", "icon", "fa-truck"),
                Map.of("name", "Van", "icon", "fa-shuttle-van"),
                Map.of("name", "Scooter", "icon", "fa-motorcycle"),
                Map.of("name", "Velo", "icon", "fa-bicycle")
        );

        model.addAttribute("vehicleTypesWithIcons", vehicleTypesWithIcons);

        return "home/home";
    }

    @GetMapping("/login")
    public String login() {
        // 重定向到已有的登录页面
        return "redirect:/utilisateur.connexion/login";
    }

    @GetMapping("/map")
    public String showMap() {
        return "map"; // 地图专用页面（可选）
    }


}
