package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.OptionPayante;
import com.example.gestion_location_vehicule.service.OptionPayanteService.OptionPayanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/options-payantes")
public class OptionPayanteController {

    private final OptionPayanteService optionPayanteService;

    public OptionPayanteController(OptionPayanteService optionPayanteService) {
        this.optionPayanteService = optionPayanteService;
    }

    // 🔹 GET : toutes les options
    @GetMapping
    public List<OptionPayante> getAll() {
        return optionPayanteService.getAllOptions();
    }

    // 🔹 GET : option par ID
    @GetMapping("/{id}")
    public Optional<OptionPayante> getById(@PathVariable Long id) {
        return optionPayanteService.getOptionById(id);
    }

    // 🔹 POST : créer une option
    @PostMapping
    public OptionPayante create(@RequestBody OptionPayante option) {
        return optionPayanteService.saveOption(option);
    }

    // 🔹 PUT : mettre à jour une option
    @PutMapping("/{id}")
    public OptionPayante update(@PathVariable Long id, @RequestBody OptionPayante option) {
        option.setId(id);
        return optionPayanteService.saveOption(option);
    }

    // 🔹 DELETE : supprimer une option
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        optionPayanteService.deleteOption(id);
    }

    // 🔹 GET : options actives
    @GetMapping("/actives")
    public List<OptionPayante> getActive() {
        return optionPayanteService.getActiveOptions();
    }

    // 🔹 GET : options inactives
    @GetMapping("/inactives")
    public List<OptionPayante> getInactive() {
        return optionPayanteService.getInactiveOptions();
    }

    // 🔹 GET : recherche par nom exact
    @GetMapping("/nom/{nom}")
    public List<OptionPayante> getByNom(@PathVariable String nom) {
        return optionPayanteService.getByNom(nom);
    }

    // 🔹 GET : recherche par nom partiel
    @GetMapping("/nom/like")
    public List<OptionPayante> getByNomContaining(@RequestParam String nom) {
        return optionPayanteService.getByNomContaining(nom);
    }

    // 🔹 GET : prix minimum
    @GetMapping("/prix/min/{prix}")
    public List<OptionPayante> getByPrixMin(@PathVariable double prix) {
        return optionPayanteService.getByPrixMin(prix);
    }

    // 🔹 GET : prix maximum
    @GetMapping("/prix/max/{prix}")
    public List<OptionPayante> getByPrixMax(@PathVariable double prix) {
        return optionPayanteService.getByPrixMax(prix);
    }

    // 🔹 GET : prix entre min et max
    @GetMapping("/prix")
    public List<OptionPayante> getByPrixBetween(@RequestParam double min, @RequestParam double max) {
        return optionPayanteService.getByPrixBetween(min, max);
    }
}
