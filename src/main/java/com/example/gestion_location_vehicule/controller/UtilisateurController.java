package com.example.gestion_location_vehicule.controller;


import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;


    @PostMapping("/connexion")
    public ResponseEntity<?> connexionUser (@RequestBody ConnexionRequest connexionRequest)
    {
        try{
            long connexionOk = utilisateurService.connexionUser(connexionRequest);

            if(connexionOk != -1)
                return ResponseEntity.ok("Connexion ok");
            else
                return ResponseEntity.ok("Connexion not ok");

        } catch(Exception e)
        {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }
}
