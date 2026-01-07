package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.OptionPayante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionPayanteRepository extends JpaRepository<OptionPayante, Long> {

    List<OptionPayante> findByActiveTrue();
}
