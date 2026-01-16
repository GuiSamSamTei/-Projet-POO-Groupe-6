package com.example.gestion_location_vehicule.service.MotoService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Moto;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.MotoRepository;
import com.example.gestion_location_vehicule.request.MotoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoService implements IMotoService {

    private final MotoRepository motoRepository;
    private final AgentRepository agentRepository;

    @Override
    public List<Moto> getAllMotos() {
        return motoRepository.findAll();
    }

    @Override
    public Optional<Moto> getMotoById(Long id) {
        return motoRepository.findById(id);
    }

    public Moto ajouterMoto(MotoRequest request) {
        Moto moto = new Moto();

        moto.setMarque(request.getMarque());
        moto.setModele(request.getModele());
        moto.setPrixjour(request.getPrixjour());
        moto.setCouleur(request.getCouleur());
        moto.setVilledispo(request.getVilledispo());
        moto.setVehiculedispo(request.getVehiculedispo());
        // Pas de note, par défaut 0.0

        moto.setCylindree(request.getCylindree());
        moto.setNbchevaux(request.getNbchevaux());

        if (request.getAgent_id() != null) {
            Optional<Agent> agentOptional = agentRepository.findById(request.getAgent_id());
            if (agentOptional.isPresent()) {
                moto.setAgent(agentOptional.get());
            } else {
                throw new IllegalArgumentException("Agent non trouvé avec l'ID : " + request.getAgent_id());
            }
        }

        return motoRepository.save(moto);
    }

    public Moto modifierMoto(Long id, MotoRequest request) {
        return motoRepository.findById(id).map(moto -> {
            // Champs communs
            if (request.getMarque() != null) moto.setMarque(request.getMarque());
            if (request.getModele() != null) moto.setModele(request.getModele());
            if (request.getPrixjour() != 0) moto.setPrixjour(request.getPrixjour());
            if (request.getCouleur() != null) moto.setCouleur(request.getCouleur());
            if (request.getVilledispo() != null) moto.setVilledispo(request.getVilledispo());
            if (request.getVehiculedispo() != null) moto.setVehiculedispo(request.getVehiculedispo());

            // Champs spécifiques Moto
            if (request.getCylindree() != null) moto.setCylindree(request.getCylindree());
            if (request.getNbchevaux() != null) moto.setNbchevaux(request.getNbchevaux());

            return motoRepository.save(moto);
        }).orElseThrow(() -> new IllegalArgumentException("Moto non trouvée avec l'ID " + id));
    }

    @Override
    public Moto saveMoto(Moto moto) {
        if (moto.getVehiculedispo() == null) {
            moto.setVehiculedispo(true);
        } // ou false
        return motoRepository.save(moto);
    }

    @Override
    public void deleteMoto(Long id) {
        motoRepository.deleteById(id);
    }

    @Override
    public List<Moto> getMotosDispo() {
        return motoRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Moto> getMotosByVille(String ville) {
        return motoRepository.findByVilledispo(ville);
    }

    @Override
    public List<Moto> getMotosByCylindreeMin(int cylindreeMin) {
        return motoRepository.findByCylindreeGreaterThanEqual(cylindreeMin);
    }

    @Override
    public List<Moto> getMotosByCylindreeMax(int cylindreeMax) {
        return motoRepository.findByCylindreeLessThanEqual(cylindreeMax);
    }

    @Override
    public List<Moto> getMotosByCylindreeBetween(int min, int max) {
        return motoRepository.findByCylindreeBetween(min, max);
    }

    @Override
    public List<Moto> getMotosByNbChevauxMin(int nbChevauxMin) {
        return motoRepository.findByNbchevauxGreaterThanEqual(nbChevauxMin);
    }

    @Override
    public List<Moto> getMotosByNbChevauxMax(int nbChevauxMax) {
        return motoRepository.findByNbchevauxLessThanEqual(nbChevauxMax);
    }

    @Override
    public List<Moto> getMotosByNbChevauxBetween(int min, int max) {
        return motoRepository.findByNbchevauxBetween(min, max);
    }

    @Override
    public List<Moto> getMotosByVilleAndCylindreeMin(String ville, int cylindreeMin) {
        return motoRepository.findByVilledispoAndVehiculedispoTrueAndCylindreeGreaterThanEqual(ville, cylindreeMin);
    }

    @Override
    public List<Moto> getMotosByVilleAndNbChevauxMin(String ville, int nbChevauxMin) {
        return motoRepository.findByVilledispoAndVehiculedispoTrueAndNbchevauxGreaterThanEqual(ville, nbChevauxMin);
    }
}
