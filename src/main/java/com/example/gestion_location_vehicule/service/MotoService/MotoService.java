package com.example.gestion_location_vehicule.service.MotoService;

import com.example.gestion_location_vehicule.model.Moto;
import com.example.gestion_location_vehicule.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService implements IMotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    // 🔹 CRUD
    @Override
    public List<Moto> getAllMotos() {
        return motoRepository.findAll();
    }

    @Override
    public Optional<Moto> getMotoById(Long id) {
        return motoRepository.findById(id);
    }

    @Override
    public Moto saveMoto(Moto moto) {
        return motoRepository.save(moto);
    }

    @Override
    public void deleteMoto(Long id) {
        motoRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
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
