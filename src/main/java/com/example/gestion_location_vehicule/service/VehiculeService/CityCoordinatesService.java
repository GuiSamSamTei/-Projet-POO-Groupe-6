package com.example.gestion_location_vehicule.service.VehiculeService;


import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@Service
public class CityCoordinatesService {

    private static final Map<String, double[]> CITY_COORDINATES = new HashMap<>();

    static {
        CITY_COORDINATES.put("Paris", new double[]{48.8566, 2.3522});
        CITY_COORDINATES.put("Lyon", new double[]{45.7640, 4.8357});
        CITY_COORDINATES.put("Marseille", new double[]{43.2965, 5.3698});
        CITY_COORDINATES.put("Bordeaux", new double[]{44.8378, -0.5792});
        CITY_COORDINATES.put("Lille", new double[]{50.6292, 3.0573});
        CITY_COORDINATES.put("Toulouse", new double[]{43.6047, 1.4442});
        CITY_COORDINATES.put("Nice", new double[]{43.7102, 7.2620});
        CITY_COORDINATES.put("Nantes", new double[]{47.2184, -1.5536});
        CITY_COORDINATES.put("Strasbourg", new double[]{48.5734, 7.7521});
        CITY_COORDINATES.put("Montpellier", new double[]{43.6119, 3.8772});
        CITY_COORDINATES.put("Rennes", new double[]{48.1173, -1.6778});
        CITY_COORDINATES.put("Le Havre", new double[]{49.4944, 0.1079});
        CITY_COORDINATES.put("Reims", new double[]{49.2583, 4.0317});
        CITY_COORDINATES.put("Saint-Étienne", new double[]{45.4397, 4.3872});
        CITY_COORDINATES.put("Toulon", new double[]{43.1242, 5.9280});
        CITY_COORDINATES.put("Grenoble", new double[]{45.1885, 5.7245});
        CITY_COORDINATES.put("Dijon", new double[]{47.3220, 5.0415});
        CITY_COORDINATES.put("Angers", new double[]{47.4784, -0.5632});
        CITY_COORDINATES.put("Nîmes", new double[]{43.8367, 4.3601});
        CITY_COORDINATES.put("Villeurbanne", new double[]{45.7667, 4.8833});
        CITY_COORDINATES.put("Clermont-Ferrand", new double[]{45.7772, 3.0820});
        CITY_COORDINATES.put("Le Mans", new double[]{48.0061, 0.1996});
        CITY_COORDINATES.put("Aix-en-Provence", new double[]{43.5297, 5.4474});
        CITY_COORDINATES.put("Brest", new double[]{48.3904, -4.4861});
        CITY_COORDINATES.put("Tours", new double[]{47.3941, 0.6848});
        CITY_COORDINATES.put("Amiens", new double[]{49.8941, 2.2958});
        CITY_COORDINATES.put("Limoges", new double[]{45.8350, 1.2611});
        CITY_COORDINATES.put("Annecy", new double[]{45.8992, 6.1294});
        CITY_COORDINATES.put("Perpignan", new double[]{42.6986, 2.8956});
        CITY_COORDINATES.put("Boulogne-Billancourt", new double[]{48.8352, 2.2409});
        CITY_COORDINATES.put("Besançon", new double[]{47.2378, 6.0241});
        CITY_COORDINATES.put("Orléans", new double[]{47.9029, 1.9093});
        CITY_COORDINATES.put("Metz", new double[]{49.1193, 6.1757});
        CITY_COORDINATES.put("Rouen", new double[]{49.4432, 1.0999});
        CITY_COORDINATES.put("Mulhouse", new double[]{47.7508, 7.3359});
        CITY_COORDINATES.put("Caen", new double[]{49.1829, -0.3707});
        CITY_COORDINATES.put("Nancy", new double[]{48.6921, 6.1844});
        CITY_COORDINATES.put("Argenteuil", new double[]{48.9472, 2.2474});
        CITY_COORDINATES.put("Montreuil", new double[]{48.8611, 2.4436});
        CITY_COORDINATES.put("Saint-Denis", new double[]{48.9356, 2.3539});
        CITY_COORDINATES.put("Roubaix", new double[]{50.6901, 3.1817});
        CITY_COORDINATES.put("Tourcoing", new double[]{50.7239, 3.1612});
        CITY_COORDINATES.put("Nanterre", new double[]{48.8925, 2.2153});
        CITY_COORDINATES.put("Avignon", new double[]{43.9493, 4.8055});
        CITY_COORDINATES.put("Vitry-sur-Seine", new double[]{48.7872, 2.3928});
        CITY_COORDINATES.put("Créteil", new double[]{48.7904, 2.4556});
        CITY_COORDINATES.put("Dunkerque", new double[]{51.0344, 2.3768});
        CITY_COORDINATES.put("Poitiers", new double[]{46.5802, 0.3402});
        CITY_COORDINATES.put("Asnières-sur-Seine", new double[]{48.9106, 2.2852});
        CITY_COORDINATES.put("Courbevoie", new double[]{48.8968, 2.2567});
        CITY_COORDINATES.put("Versailles", new double[]{48.8014, 2.1301});

    }

    public double[] getCoordinates(String city) {
        String normalizedCity = normalizeCityName(city);

        if (CITY_COORDINATES.containsKey(normalizedCity)) {
            return CITY_COORDINATES.get(normalizedCity);
        }

        for (String knownCity : CITY_COORDINATES.keySet()) {
            if (knownCity.toLowerCase().contains(normalizedCity.toLowerCase()) ||
                    normalizedCity.toLowerCase().contains(knownCity.toLowerCase())) {
                return CITY_COORDINATES.get(knownCity);
            }
        }

        // Toulouse
        return new double[]{43.6047, 1.4442};
    }

    private String normalizeCityName(String city) {
        if (city == null || city.trim().isEmpty()) {
            return "Toulouse";
        }
        city = city.trim();
        if (city.length() > 1) {
            return city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
        }
        return city;
    }

    public List<String> getAllCities() {
        return new ArrayList<>(CITY_COORDINATES.keySet());
    }
}
