package com.example.gestion_location_vehicule.controller;


import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.LoueurService.LoueurService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/historique-location")
@RequiredArgsConstructor
public class ContratlocationMVCController {

    private final ContratlocationService contratlocationService;
    private final LoueurService loueurService;


    @GetMapping
    public String contratlocation(Model model, HttpSession session){

        if(session.getAttribute("user")==null){
            return "redirect:utilisateur/connexion/login";
        }

        Long id = (Long)session.getAttribute("user");

        Loueur loueur = loueurService.getById(id);

        List<Contratlocation> contratlocationList = contratlocationService.trouverContraByLoueurId(id);


        model.addAttribute("contratlocationList",contratlocationList);



        return "loueur/historiquelocation";


    }


}
