package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findByPayeeFalse();

    List<Facture> findByDateFactureBetween(
            java.time.LocalDate debut,
            java.time.LocalDate fin
    );
}
