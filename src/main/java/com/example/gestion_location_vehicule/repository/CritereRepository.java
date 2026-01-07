package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.enums.TypeCritere;
import com.example.gestion_location_vehicule.model.Critere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CritereRepository extends JpaRepository<Critere, Long> {

    Critere findByNom(String nom);

    List<Critere> findByType(TypeCritere type);
}
