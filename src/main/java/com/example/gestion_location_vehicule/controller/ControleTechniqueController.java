package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.ControleTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.ControleTechniqueService.IControleTechniqueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/controletechnique")
public class ControleTechniqueController {

    private final IControleTechniqueService controleTechniqueService;
    private final VehiculeRepository vehiculeRepository;

    public ControleTechniqueController(
            IControleTechniqueService controleTechniqueService,
            VehiculeRepository vehiculeRepository
    ) {
        this.controleTechniqueService = controleTechniqueService;
        this.vehiculeRepository = vehiculeRepository;
    }

    // AFFICHER LE FORMULAIRE
    @GetMapping("/nouveau/{vehiculeId}")
    public String afficherFormulaire(
            @PathVariable Long vehiculeId,
            Model model
    ) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        ControleTechnique controleTechnique = new ControleTechnique();
        controleTechnique.setVehicule(vehicule);

        model.addAttribute("controleTechnique", controleTechnique);
        model.addAttribute("vehicule", vehicule);

        return "controletechnique/controleTechnique";
    }

    // ENREGISTRER
    @PostMapping("/enregistrer")
    public String enregistrerControleTechnique(
            @ModelAttribute ControleTechnique controleTechnique
    ) {
        controleTechniqueService.enregistrerControleTechnique(controleTechnique);
        return "redirect:/vehicules/liste";
    }
}
