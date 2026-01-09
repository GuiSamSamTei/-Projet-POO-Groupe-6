package com.example.gestion_location_vehicule.service.VehiculeService;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.request.VehiculeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class VehiculeService implements IVehiculeService{

    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Vehicule> getAllVehicules() {

        return vehiculeRepository.findAll();
    }

    @Override
    public List<Vehicule> getVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin) {

        return List.of();
    }

    @Override
    public List<Vehicule> getVehiculesParVille(String ville) {
        return vehiculeRepository.findByVilledispo(ville);
    }

    @Override
    public List<Vehicule> getVehiculesParAgent(Long agentId) {
        return vehiculeRepository.findByAgentId(agentId);
    }

//    @Override
//    public List<Vehicule> filtreVehicules(FiltreVehiculeRequest filtre) {
//        return List.of();
//    }

    @Override
    public Double calculePrixLocation(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin, boolean avecAssurance, List<String> options) {
        return 0.0;
    }

    @Override
    public boolean verifierDisponibilite(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin) {
        return false;
    }

    @Override
    public Double getNoteMoyenne(Long vehiculeId) {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findById(vehiculeId);

        if(!vehiculeOptional.isEmpty())
        {
            Vehicule vehicule = vehiculeOptional.get();

            return vehicule.getNotevehicule();
        }
        return 0.0;
    }

    @Override
    public Vehicule mettreAJourDisponibilite(Long vehiculeId, boolean disponible) {
        return null;
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

}
