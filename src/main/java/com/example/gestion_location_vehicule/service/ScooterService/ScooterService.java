package com.example.gestion_location_vehicule.service.ScooterService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Scooter;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.ScooterRepository;
import com.example.gestion_location_vehicule.request.ScooterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterService implements IScooterService {

    private final ScooterRepository scooterRepository;
    private final AgentRepository agentRepository;

    // 🔹 CRUD
    @Override
    public List<Scooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    @Override
    public Optional<Scooter> getScooterById(Long id) {
        return scooterRepository.findById(id);
    }

    public Scooter ajouterScooter(ScooterRequest request) {
        Scooter scooter = new Scooter();

        // 1. Champs communs
        scooter.setMarque(request.getMarque());
        scooter.setModele(request.getModele());
        scooter.setPrixjour(request.getPrixjour());
        scooter.setCouleur(request.getCouleur());
        scooter.setVilledispo(request.getVilledispo());
        scooter.setVehiculedispo(request.getVehiculedispo());
        // Note par défaut 0.0

        // 2. Champs spécifiques Scooter
        scooter.setCylindree(request.getCylindree() != null ? request.getCylindree() : 0);
        scooter.setElectrique(request.getElectrique() != null ? request.getElectrique() : false);

        // 3. Liaison Agent
        if (request.getAgent_id() != null) {
            Optional<Agent> agentOptional = agentRepository.findById(request.getAgent_id());
            if (agentOptional.isPresent()) {
                scooter.setAgent(agentOptional.get());
            } else {
                throw new IllegalArgumentException("Agent non trouvé avec l'ID : " + request.getAgent_id());
            }
        }

        return scooterRepository.save(scooter);
    }

    @Override
    public Scooter saveScooter(Scooter scooter) {
        return scooterRepository.save(scooter);
    }

    @Override
    public void deleteScooter(Long id) {
        scooterRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<Scooter> getAvailableScooters() {
        return scooterRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Scooter> getByVille(String ville) {
        return scooterRepository.findByVilledispo(ville);
    }

    @Override
    public List<Scooter> getByCylindreeMin(int cylindreeMin) {
        return scooterRepository.findByCylindreeGreaterThanEqual(cylindreeMin);
    }

    @Override
    public List<Scooter> getByCylindreeMax(int cylindreeMax) {
        return scooterRepository.findByCylindreeLessThanEqual(cylindreeMax);
    }

    @Override
    public List<Scooter> getByCylindreeBetween(int min, int max) {
        return scooterRepository.findByCylindreeBetween(min, max);
    }

    @Override
    public List<Scooter> getElectrique() {
        return scooterRepository.findByElectriqueTrue();
    }

    @Override
    public List<Scooter> getNonElectrique() {
        return scooterRepository.findByElectriqueFalse();
    }

    @Override
    public List<Scooter> getByVilleAndCylindreeMin(String ville, int cylindreeMin) {
        return scooterRepository.findByVilledispoAndVehiculedispoTrueAndCylindreeGreaterThanEqual(ville, cylindreeMin);
    }

    @Override
    public List<Scooter> getByVilleAndElectrique(String ville) {
        return scooterRepository.findByVilledispoAndVehiculedispoTrueAndElectriqueTrue(ville);
    }
}
