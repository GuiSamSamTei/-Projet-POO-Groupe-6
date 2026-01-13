package com.example.gestion_location_vehicule.service.ContratlocationService;


import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.repository.ContralocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratlocationService implements IContratlocationService{

    private final ContralocationRepository contralocationRepository;

    @Override
    public Contratlocation ajouterContralocation(Contratlocation contratlocation) {
        return contralocationRepository.save(contratlocation);
    }

    @Override
    public Contratlocation modifierContralocation(Long contra_id, Contratlocation contratlocation) {
        Optional<Contratlocation> contratlocationOptional = contralocationRepository.findById(contra_id);

        Contratlocation contratlocationexiste = contratlocationOptional.get();

        contratlocationexiste.setDatedebut(contratlocation.getDatedebut());
        contratlocationexiste.setDatefin(contratlocation.getDatefin());

        return contralocationRepository.save(contratlocationexiste);


    }

    @Override
    public void  supprimerContralocation(Long contra_id) {
        contralocationRepository.deleteById(contra_id);
    }

    @Override
    public List<Contratlocation> getAllContralocations() {
        return contralocationRepository.findAll();
    }

    @Override
    public Contratlocation trouverContraByLoueurId(Long loueur_id) {
        return contralocationRepository.findByLoueurId(loueur_id);
    }

    @Override
    public Contratlocation trouverContraByAssurence(Long assurance_id) {
        return contralocationRepository.findByAssuranceId(assurance_id);
    }

    @Override
    public Contratlocation trouverContraByVehiculeId(Long vehicule_id) {
        return contralocationRepository.findByVehiculeId(vehicule_id);
    }
}