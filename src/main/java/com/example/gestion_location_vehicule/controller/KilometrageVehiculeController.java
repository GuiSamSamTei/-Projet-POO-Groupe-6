package com.example.gestion_location_vehicule.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gestion_location_vehicule.model.KilometrageVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.KilometrageVehiculeService.IKilometrageVehiculeService;

@Controller
@RequestMapping("/kilometrage")
public class KilometrageVehiculeController {

    private final IKilometrageVehiculeService kmService;
    private final VehiculeRepository vehiculeRepository;

    public KilometrageVehiculeController(IKilometrageVehiculeService kmService, VehiculeRepository vehiculeRepository) {
        this.kmService = kmService;
        this.vehiculeRepository = vehiculeRepository;
    }

    @GetMapping("/{vehiculeId}")
    public String afficherFormulaire(@PathVariable Long vehiculeId, Model model) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        KilometrageVehicule kmVehicule = new KilometrageVehicule();
        kmVehicule.setVehicule(vehicule);
        kmVehicule.setDateLocation(LocalDate.now());

        model.addAttribute("vehicule", vehicule);
        model.addAttribute("kmVehicule", kmVehicule);
        model.addAttribute("historique", kmService.getKilometragesByVehicule(vehicule));

        return "kilometrage/formulairekilometrage";
    }

    @PostMapping("/enregistrer")
    public String enregistrerKilometrage(
            @ModelAttribute KilometrageVehicule kmVehicule,
            @RequestParam("photoDepartFile") MultipartFile photoDepartFile,
            @RequestParam("photoRetourFile") MultipartFile photoRetourFile
    ) throws IOException {

        if (!photoDepartFile.isEmpty()) {
            String cheminDepart = "uploads/" + photoDepartFile.getOriginalFilename();
            photoDepartFile.transferTo(new File(cheminDepart));
            kmVehicule.setPhotoDepart(cheminDepart);
        }

        if (!photoRetourFile.isEmpty()) {
            String cheminRetour = "uploads/" + photoRetourFile.getOriginalFilename();
            photoRetourFile.transferTo(new File(cheminRetour));
            kmVehicule.setPhotoRetour(cheminRetour);
        }

        kmService.enregistrerKilometrage(kmVehicule);

        return "redirect:/kilometrage/" + kmVehicule.getVehicule().getId();
    }
}
