package com.example.gestion_location_vehicule.service.VehiculeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.gestion_location_vehicule.model.DisponibiliteVehicule;
import org.springframework.stereotype.Service;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.specification.VehiculeSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class VehiculeService implements IVehiculeService{

    private final VehiculeRepository vehiculeRepository;

    @Override
    public Vehicule getVehiculeByid(Long id) {
        return vehiculeRepository.findById(id).get();
    }

    @Override
    public List<Vehicule> getAllVehicules() {

        return vehiculeRepository.findAll();
    }

    @Override
    public List<Vehicule> getAllVehiculesDispo() {
        return List.of();
    }

    @Override
    public List<Vehicule> getVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin) {

        return vehiculeRepository.findByVehiculedispo(true);

    }

    @Override
    public List<Vehicule> getVehiculesParVille(String ville) {
        return vehiculeRepository.findByVilledispo(ville);
    }

    @Override
    public List<Vehicule> getVehiculesParAgent(Long agentId) {
        return vehiculeRepository.findByAgentId(agentId);
    }


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

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> filtrer(Map<String, String> filters) {
        return vehiculeRepository.findAll(
                VehiculeSpecification.withFilters(filters)
        );
    }



    public boolean estDisponible(Long vehiculeId,
                                 LocalDate dateDebut,
                                 LocalDate dateFin) {

        Vehicule vehicule =
                vehiculeRepository.findById(vehiculeId).get();

        if (!vehicule.getVehiculedispo())
            return false;

        if(vehicule.getDisponibilites()==null)
        {
            return false;
        }

        if(vehicule.getDisponibilites().size()==0)
            return false;


        for (DisponibiliteVehicule dispo : vehicule.getDisponibilites()) {

            boolean debutOK =
                    !dateDebut.isBefore(dispo.getDateDebut());


            boolean finOK =
                    !dateFin.isAfter(dispo.getDateFin());


            if (debutOK && finOK) {
                return true;
            }
        }
        return false;
    }



}
