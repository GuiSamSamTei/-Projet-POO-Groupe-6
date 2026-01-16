package com.example.gestion_location_vehicule.specification;

import com.example.gestion_location_vehicule.model.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehiculeSpecification {

    public static Specification<Vehicule> withFilters(Map<String, String> filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.containsKey("dateDebut") && filters.containsKey("dateFin")
                    && !filters.get("dateDebut").isEmpty()
                    && !filters.get("dateFin").isEmpty()) {

                LocalDate dateDebut = LocalDate.parse(filters.get("dateDebut"));
                LocalDate dateFin   = LocalDate.parse(filters.get("dateFin"));

                Join<Vehicule, DisponibiliteVehicule> dispoJoin =
                        root.join("disponibilites", JoinType.INNER);

                predicates.add(
                        cb.and(
                                cb.isTrue(root.get("vehiculedispo")),
                                cb.lessThanOrEqualTo(dispoJoin.get("dateDebut"), dateDebut),
                                cb.greaterThanOrEqualTo(dispoJoin.get("dateFin"), dateFin)
                        )
                );

                query.distinct(true);
            }


            if (filters.containsKey("marque") && !filters.get("marque").isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("marque")),
                        "%" + filters.get("marque").toLowerCase() + "%"));
            }

            if (filters.containsKey("modele") && !filters.get("modele").isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("modele")),
                        "%" + filters.get("modele").toLowerCase() + "%"));
            }

            if (filters.containsKey("villedispo") && !filters.get("villedispo").isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("villedispo")),
                        "%" + filters.get("villedispo").toLowerCase() + "%"));
            }

            if (filters.containsKey("prixjour") && !filters.get("prixjour").isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("prixjour"),
                        Double.valueOf(filters.get("prixjour"))));
            }

            if (filters.containsKey("notevehicule") && !filters.get("notevehicule").isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("notevehicule"),
                        Double.valueOf(filters.get("notevehicule"))));
            }

            predicates.add(cb.isTrue(root.get("vehiculedispo")));

            String type = filters.getOrDefault("type", "");
            if (!type.isEmpty()) {
                predicates.add(cb.equal(root.type(), getClassByType(type)));
            }

            switch (type.toLowerCase()) {
                case "voiture":
                    var voitureRoot = cb.treat(root, Voiture.class);
                    if (filters.containsKey("automatique") && !filters.get("automatique").isEmpty()) {
                        predicates.add(cb.equal(voitureRoot.get("automatique"),
                                Boolean.valueOf(filters.get("automatique"))));
                    }
                    if (filters.containsKey("carburant") && !filters.get("carburant").isEmpty()) {
                        predicates.add(cb.equal(voitureRoot.get("carburant"), filters.get("carburant")));
                    }
                    break;

                case "velo":
                    var veloRoot = cb.treat(root, Velo.class);
                    if (filters.containsKey("electrique") && !filters.get("electrique").isEmpty()) {
                        predicates.add(cb.equal(veloRoot.get("electrique"),
                                Boolean.valueOf(filters.get("electrique"))));
                    }
                    break;

                case "camion":
                    var camionRoot = cb.treat(root, Camion.class);

                    if (filters.containsKey("chargemax") && !filters.get("chargemax").isEmpty()) {
                        predicates.add(cb.greaterThanOrEqualTo(camionRoot.get("chargemax"),
                                Double.valueOf(filters.get("chargemax"))));
                    }

                    break;
                //MOTO
                case "moto" :
                    var motoRoot = cb.treat(root, Moto.class);

                    if (filters.containsKey("cylindree") && !filters.get("cylindree").isEmpty()) {
                        predicates.add(cb.greaterThanOrEqualTo(motoRoot.get("cylindree"),
                                Integer.valueOf(filters.get("cylindree"))));
                    }
                    if (filters.containsKey("nbchevaux") && !filters.get("nbchevaux").isEmpty()) {
                        predicates.add(cb.greaterThanOrEqualTo(motoRoot.get("nbchevaux"),
                                Integer.valueOf(filters.get("nbchevaux"))));
                    }

                    break;

                case "van":
                    var vanRoot = cb.treat(root, Velo.class);

                    if (filters.containsKey("nombreplaces") && !filters.get("nombreplaces").isEmpty()) {
                        predicates.add(cb.greaterThanOrEqualTo(vanRoot.get("nombreplaces"),
                                Integer.valueOf(filters.get("nombreplaces"))));
                    }
                    break;

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Class<? extends Vehicule> getClassByType(String type) {
        return switch (type.toLowerCase()) {
            case "voiture" -> Voiture.class;
            case "velo" -> Velo.class;
            case "camion" -> Camion.class;
            case "van" -> Van.class;
            case "scooter" -> Scooter.class;
            case "moto" -> Moto.class;
            default -> Vehicule.class;
        };
    }
}
