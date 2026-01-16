package com.example.gestion_location_vehicule.service.VanService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.model.Van;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.VanRepository;
import com.example.gestion_location_vehicule.request.VanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VanService implements IVanService {

    private final VanRepository vanRepository;
    private final AgentRepository agentRepository;

    @Override
    public List<Van> getAllVans() {
        return vanRepository.findAll();
    }

    @Override
    public Optional<Van> getVanById(Long id) {
        return vanRepository.findById(id);
    }

    public Van ajouterVan(VanRequest vanRequest) {


        Van van = new Van();
        van.setMarque(vanRequest.getMarque());
        van.setModele(vanRequest.getModele());
        van.setPrixjour(vanRequest.getPrixjour());
        van.setCouleur(vanRequest.getCouleur());
        van.setNotevehicule(vanRequest.getNotevehicule());
        van.setVilledispo(vanRequest.getVilledispo());
        van.setVehiculedispo(vanRequest.getVehiculedispo());

        if(vanRequest.getAgent_id()!=null) {
            Optional<Agent> agentOptional = agentRepository.findById(vanRequest.getAgent_id());
            van.setAgent(agentOptional.get());
        }

        van.setNombreplaces(vanRequest.getNombreplaces());

        return vanRepository.save(van);
    }


    public Van modifierVan(Long id, VanRequest request) {
        return vanRepository.findById(id).map(van -> {

            if (request.getMarque() != null) van.setMarque(request.getMarque());
            if (request.getModele() != null) van.setModele(request.getModele());
            if (request.getPrixjour() != 0) van.setPrixjour(request.getPrixjour());
            if (request.getVilledispo() != null) van.setVilledispo(request.getVilledispo());
            if (request.getVehiculedispo() != null) van.setVehiculedispo(request.getVehiculedispo());

            if (request.getNombreplaces() != 0) van.setNombreplaces(request.getNombreplaces());

            return vanRepository.save(van);
        }).orElseThrow(() -> new RuntimeException("Van non trouvé avec l'id " + id));
    }

    @Override
    public Van saveVan(Van van) {
        return vanRepository.save(van);
    }

    @Override
    public void deleteVan(Long id) {
        vanRepository.deleteById(id);
    }

    @Override
    public List<Van> getVansDispo() {
        return vanRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Van> getVansByVille(String ville) {
        return vanRepository.findByVilledispo(ville);
    }

    @Override
    public List<Van> getVansByNombrePlacesMin(int minPlaces) {
        return vanRepository.findByNombreplacesGreaterThanEqual(minPlaces);
    }

    @Override
    public List<Van> getVansByNombrePlacesMax(int maxPlaces) {
        return vanRepository.findByNombreplacesLessThanEqual(maxPlaces);
    }

    @Override
    public List<Van> getVansByNombrePlacesBetween(int minPlaces, int maxPlaces) {
        return vanRepository.findByNombreplacesBetween(minPlaces, maxPlaces);
    }

    @Override
    public List<Van> getVansByVilleDispoEtMinPlaces(String ville, int minPlaces) {
        return vanRepository.findByVehiculedispoTrueAndVilledispoAndNombreplacesGreaterThanEqual(ville, minPlaces);
    }
}
