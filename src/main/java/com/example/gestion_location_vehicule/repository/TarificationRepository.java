package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Tarification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarificationRepository extends JpaRepository<Tarification, Long> {

    Optional<Tarification> findByAnnee(long annee);
}
