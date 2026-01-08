package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Camion;
import com.example.gestion_location_vehicule.service.CamionService.CamionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/camions")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    // GET : tous les camions
    @GetMapping
    public List<Camion> getAll() {
        return camionService.getAllCamions();
    }

    // GET : camion par ID
    @GetMapping("/{id}")
    public Optional<Camion> getById(@PathVariable Long id) {
        return camionService.getCamionById(id);
    }

    // GET : camions disponibles
    @GetMapping("/dispo")
    public List<Camion> getDispo() {
        return camionService.getCamionsDispo();
    }

    // GET : camions par ville
    @GetMapping("/ville/{ville}")
    public List<Camion> getByVille(@PathVariable String ville) {
        return camionService.getCamionsByVille(ville);
    }

    // GET : camions par charge minimale
    @GetMapping("/charge-min/{min}")
    public List<Camion> getByCharge(@PathVariable double min) {
        return camionService.getCamionsByChargemax(min);
    }

    // GET : camions par volume minimal
    @GetMapping("/volume-min/{min}")
    public List<Camion> getByVolume(@PathVariable double min) {
        return camionService.getCamionsByVolume(min);
    }

    // GET : camions par ville + dispo + charge minimale
    @GetMapping("/recherche")
    public List<Camion> getByVilleDispoAndCharge(@RequestParam String ville, @RequestParam double minCharge) {
        return camionService.getCamionsByVilleDispoAndCharge(ville, minCharge);
    }

    // POST : créer un camion
    @PostMapping
    public Camion create(@RequestBody Camion camion) {
        return camionService.saveCamion(camion);
    }

    // DELETE : supprimer un camion
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        camionService.deleteCamion(id);
    }
}
