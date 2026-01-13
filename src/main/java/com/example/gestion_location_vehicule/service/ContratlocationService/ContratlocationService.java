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

    private final ContralocationRepository contratlocationRepository;

    @Override
    public Contratlocation ajouterContralocation(Contratlocation contratlocation) {
        return contratlocationRepository.save(contratlocation);
    }

    @Override
    public Contratlocation modifierContralocation(Long contra_id, Contratlocation contratlocation) {
        Optional<Contratlocation> contratlocationOptional = contratlocationRepository.findById(contra_id);

        Contratlocation contratlocationexiste = contratlocationOptional.get();

        contratlocationexiste.setDatedebut(contratlocation.getDatedebut());
        contratlocationexiste.setDatefin(contratlocation.getDatefin());

        return contratlocationRepository.save(contratlocationexiste);


    }

    @Override
    public void  supprimerContralocation(Long contra_id) {
        contratlocationRepository.deleteById(contra_id);
    }

    @Override
    public List<Contratlocation> getAllContralocations() {
        return contratlocationRepository.findAll();
    }

    @Override
    public Contratlocation trouverContratByLoueurId(Long loueur_id) {
        return contratlocationRepository.findByLoueurId(loueur_id);
    }

    @Override
    public Contratlocation trouverContraByAssurence(Long assurance_id) {
        return contratlocationRepository.findByAssuranceId(assurance_id);
    }

    @Override
    public Contratlocation trouverContraByVehiculeId(Long vehicule_id) {
        return contratlocationRepository.findByVehiculeId(vehicule_id);
    }
}
