package com.example.gestion_location_vehicule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_location_vehicule.model.EntretienTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.EntretienTechniqueService.IEntretienTechniqueService;

@Controller
@RequestMapping("/controletechnique/entretiens")
public class EntretienTechniqueController {

    private final IEntretienTechniqueService entretienService;
    private final VehiculeRepository vehiculeRepository;

    public EntretienTechniqueController(
            IEntretienTechniqueService entretienService,
            VehiculeRepository vehiculeRepository
    ) {
        this.entretienService = entretienService;
        this.vehiculeRepository = vehiculeRepository;
    }

    @GetMapping("/{vehiculeId}")
    public String afficherFormulaire(
            @PathVariable Long vehiculeId,
            Model model
    ) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        EntretienTechnique entretien = new EntretienTechnique();
        entretien.setVehicule(vehicule);

        model.addAttribute("vehicule", vehicule);
        model.addAttribute("entretien", entretien);
        model.addAttribute("entretiens", entretienService.getEntretiensByVehicule(vehicule));

        return "controletechnique/entretiens";
    }

    @PostMapping("/enregistrer")
    public String enregistrerEntretien(@ModelAttribute EntretienTechnique entretien) {
        entretienService.enregistrerEntretien(entretien);
        return "redirect:/controletechnique/entretiens/" + entretien.getVehicule().getId();
    }
}
