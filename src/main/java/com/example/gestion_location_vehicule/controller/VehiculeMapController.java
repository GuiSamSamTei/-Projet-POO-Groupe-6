package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import com.example.gestion_location_vehicule.service.VehiculeService.CityCoordinatesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;

@RestController
@RequestMapping("/api/map")
public class VehiculeMapController {

    private final VehiculeService vehiculeService;
    private final CityCoordinatesService cityCoordinatesService;

    public VehiculeMapController(VehiculeService vehiculeService,
                                 CityCoordinatesService cityCoordinatesService) {
        this.vehiculeService = vehiculeService;
        this.cityCoordinatesService = cityCoordinatesService;
    }

    @GetMapping("/city-stats")
    public List<CityVehicleStatsDTO> getCityVehicleStats() {
        List<Vehicule> allVehicles = vehiculeService.getAllVehicules();

        Map<String, CityVehicleStatsDTO> cityStats = new HashMap<>();

        for (Vehicule vehicle : allVehicles) {

            if (vehicle.getVehiculedispo() == null || !vehicle.getVehiculedispo().booleanValue()) {
                continue;
            }

            String city = vehicle.getVilledispo();
            if (city == null || city.trim().isEmpty()) {
                city = "Toulouse";
            }

            CityVehicleStatsDTO stats = cityStats.getOrDefault(city,
                    new CityVehicleStatsDTO(city, 0, new HashMap<>()));

            stats.totalVehicles++;

            String vehicleType = vehicle.getClass().getSimpleName().toLowerCase();
            stats.vehicleTypes.put(vehicleType,
                    stats.vehicleTypes.getOrDefault(vehicleType, 0) + 1);

            cityStats.put(city, stats);
        }

        return cityStats.values().stream()
                .map(stats -> {
                    double[] coords = cityCoordinatesService.getCoordinates(stats.cityName);
                    stats.latitude = coords[0];
                    stats.longitude = coords[1];
                    return stats;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/all-vehicles")
    public List<VehicleMapDTO> getAllVehicles(@RequestParam(value = "city", required = false) String city) {
        List<Vehicule> vehicles = vehiculeService.getAllVehicules();

        return vehicles.stream()
                .filter(v -> v.getVehiculedispo() != null && v.getVehiculedispo())
                .filter(v -> {
                    if (city == null || city.trim().isEmpty()) {
                        return true;
                    }
                    return v.getVilledispo() != null &&
                            v.getVilledispo().trim().equalsIgnoreCase(city.trim());
                })
                .map(this::convertToMapDTO)
                .collect(Collectors.toList());
    }


    private VehicleMapDTO convertToMapDTO(Vehicule vehicule) {
        VehicleMapDTO dto = new VehicleMapDTO();
        dto.setId(vehicule.getId());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());

        String className = vehicule.getClass().getSimpleName();
        String vehicleType = "voiture";

        if (className.toLowerCase().contains("moto")) {
            vehicleType = "moto";
        } else if (className.toLowerCase().contains("camion")) {
            vehicleType = "camion";
        } else if (className.toLowerCase().contains("van")) {
            vehicleType = "van";
        } else if (className.toLowerCase().contains("scooter")) {
            vehicleType = "scooter";
        } else if (className.toLowerCase().contains("velo")) {
            vehicleType = "velo";
        }

        dto.setType(vehicleType);
        dto.setPrixJournalier(vehicule.getPrixjour());
        dto.setCity(vehicule.getVilledispo());

        double[] baseCoords;
        if (vehicule.getVilledispo() != null && !vehicule.getVilledispo().trim().isEmpty()) {
            baseCoords = cityCoordinatesService.getCoordinates(vehicule.getVilledispo());
        } else {
            baseCoords = new double[]{43.6047, 1.4442};
        }

        long seed = vehicule.getId() != null ? vehicule.getId() : System.nanoTime();

        double randomLat = (hashCodeToDouble(seed * 31) * 0.02) - 0.01;
        double randomLng = (hashCodeToDouble(seed * 37) * 0.02) - 0.01;

        dto.setLatitude(baseCoords[0] + randomLat);
        dto.setLongitude(baseCoords[1] + randomLng);

        return dto;
    }

    private double hashCodeToDouble(long value) {
        long hash = value;
        hash = hash * 16777619;
        hash ^= hash >>> 16;
        hash = hash * 16777619;

        return (double)(hash & 0x7FFFFFFF) / (double)0x7FFFFFFF;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CityVehicleStatsDTO {
        public String cityName;
        public int totalVehicles;
        public Map<String, Integer> vehicleTypes;
        public double latitude;
        public double longitude;

        public CityVehicleStatsDTO(String cityName, int totalVehicles, Map<String, Integer> vehicleTypes) {
            this.cityName = cityName;
            this.totalVehicles = totalVehicles;
            this.vehicleTypes = vehicleTypes;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VehicleMapDTO {
        private Long id;
        private String marque;
        private String modele;
        private String type;
        private String city;
        private Double latitude;
        private Double longitude;
        private Double prixJournalier;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getMarque() { return marque; }
        public void setMarque(String marque) { this.marque = marque; }

        public String getModele() { return modele; }
        public void setModele(String modele) { this.modele = modele; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }

        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }

        public Double getPrixJournalier() { return prixJournalier; }
        public void setPrixJournalier(Double prixJournalier) { this.prixJournalier = prixJournalier; }
    }
}