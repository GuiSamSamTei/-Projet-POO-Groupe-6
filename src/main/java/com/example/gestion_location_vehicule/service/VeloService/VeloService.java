package com.example.gestion_location_vehicule.service.VeloService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Velo;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.VeloRepository;
import com.example.gestion_location_vehicule.request.VeloRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VeloService implements IVeloService{

    private final VeloRepository veloRepository;
    private final AgentRepository agentRepository;
    @Override
    public List<Velo> getAllVelo() {
        return veloRepository.findAll();
    }

    @Override
    public Velo ajouterVelo(VeloRequest veloRequest) {

        Velo velo = new Velo();

        velo.setCouleur(veloRequest.getCouleur());
        velo.setElectrique(veloRequest.getElectrique() != null ? veloRequest.getElectrique() : false);
        velo.setTypeVelo(veloRequest.getTypeVelo());
        velo.setNombreVitesses(veloRequest.getNombrevitesses());

        if(veloRequest.getAgent_id()!=null) {
            Optional<Agent> agentOptional = agentRepository.findById(veloRequest.getAgent_id());
            velo.setAgent(agentOptional.get());
        }

        velo.setMarque(veloRequest.getMarque());
        velo.setModele(veloRequest.getModele());
        velo.setPrixjour(veloRequest.getPrixjour());
        velo.setCouleur(veloRequest.getCouleur());
        velo.setVehiculedispo(veloRequest.getVehiculedispo());
        velo.setVilledispo(veloRequest.getVilledispo());

        return veloRepository.save(velo);
    }

    @Override
    public Velo modifierVelo(VeloRequest veloRequest, Long velo_id) {
        Optional<Velo> veloOptional = veloRepository.findById(velo_id);

        if (veloOptional.isEmpty()) {
            throw new IllegalArgumentException("Vélo non trouvé avec l'ID: " + veloRequest.getAgent_id());
        }

        Velo veloExistante = veloOptional.get();

        if (veloRequest.getMarque() != null) {
            veloExistante.setMarque(veloRequest.getMarque());
        }

        if (veloRequest.getModele() != null) {
            veloExistante.setModele(veloRequest.getModele());
        }

        if (veloRequest.getCouleur() != null) {
            veloExistante.setCouleur(veloRequest.getCouleur());
        }

        if (veloRequest.getTypeVelo() != null) {

            String nouveauType = veloRequest.getTypeVelo();
            veloExistante.setTypeVelo(nouveauType);
        }

        if (veloRequest.getNombrevitesses() != 0) {
            veloExistante.setNombreVitesses(veloRequest.getNombrevitesses());
        }

        if (veloRequest.getElectrique() != null) {
            veloExistante.setElectrique(veloRequest.getElectrique());
        }

        return veloRepository.save(veloExistante);
        }

    @Override
    public void supprimerVelo(Long velo_id) {

        veloRepository.deleteById(velo_id);
    }

    @Override
    public Optional<Velo> getVeloById(Long id) {
        return veloRepository.findById(id);
    }
}
