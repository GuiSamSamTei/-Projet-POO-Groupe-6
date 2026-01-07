package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Tarification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarificationRepository extends JpaRepository<Tarification, Long> {

    // Récupérer la tarification pour une année spécifique
    Optional<Tarification> findByAnnee(long annee);

    // Récupérer toutes les tarifications dont le prix fixe est supérieur à une valeur
    List<Tarification> findByPrixfixeGreaterThanEqual(double prixMin);

    // Récupérer toutes les tarifications dont le prix fixe est inférieur à une valeur
    List<Tarification> findByPrixfixeLessThanEqual(double prixMax);

    // Récupérer toutes les tarifications dont le pourcentage est supérieur ou égal à une valeur
    List<Tarification> findByPourcentageGreaterThanEqual(double pourcentageMin);

    // Récupérer toutes les tarifications dont le pourcentage est inférieur ou égal à une valeur
    List<Tarification> findByPourcentageLessThanEqual(double pourcentageMax);

    // Combinaison : prix fixe et pourcentage
    List<Tarification> findByPrixfixeLessThanEqualAndPourcentageLessThanEqual(double prixMax, double pourcentageMax);
}
