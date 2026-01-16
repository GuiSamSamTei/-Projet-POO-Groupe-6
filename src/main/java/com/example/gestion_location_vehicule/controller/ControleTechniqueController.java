package com.example.gestion_location_vehicule.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_location_vehicule.model.ControleTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.ControleTechniqueService.IControleTechniqueService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;

@Controller
@RequestMapping("/controletechnique")
public class ControleTechniqueController {

    private final IControleTechniqueService controleTechniqueService;
    private final VehiculeRepository vehiculeRepository;
    private final VehiculeService vehiculeService;

    public ControleTechniqueController(
            IControleTechniqueService controleTechniqueService,
            VehiculeRepository vehiculeRepository, VehiculeService vehiculeService
    ) {
        this.controleTechniqueService = controleTechniqueService;
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeService = vehiculeService;
    }

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

    @PostMapping("/enregistrer")
    public String enregistrerControleTechnique(
            @ModelAttribute("controleTechnique") ControleTechnique controleTechnique
    ) {
        controleTechniqueService.enregistrerControleTechnique(controleTechnique);


        Long vId = controleTechnique.getVehicule().getId();

        return "redirect:/controletechnique/vehicule/" + vId + "/controles";
    }
    @GetMapping("/vehicule/{id}/controles")
    public String afficherHistoriqueControles(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehiculeService.getVehiculeByid(id);

        if(vehicule==null)
            return "redirect:/vehicule/liste";
        List<ControleTechnique> controles = controleTechniqueService.getControlesByVehiculeID(id);

        model.addAttribute("vehicule", vehicule);
        model.addAttribute("controles", controles);
        model.addAttribute("aujourdhui", LocalDate.now());

        return "controletechnique/affichercontroles";
    }
}
