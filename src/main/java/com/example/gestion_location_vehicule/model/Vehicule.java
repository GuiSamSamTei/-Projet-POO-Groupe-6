package com.example.gestion_location_vehicule.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "DTYPE",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicule_seq")
    @SequenceGenerator(name = "vehicule_seq", sequenceName = "VEHICULE_SEQ", allocationSize = 1)
    private Long id;

    private String marque;
    private String modele;
    private String couleur;
    private double notevehicule;

    @Column(nullable = false)
    private Boolean vehiculedispo;

    @Temporal(TemporalType.DATE)
    private Date datedispo;

    private String villedispo;
    private double kilometrage;
    private double prixjour;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToMany(mappedBy = "vehicule")
    private List<EvalV> evalrecues;

    @OneToMany(mappedBy = "vehicule")
    private List<PrixAssurance> prixassurance;

    @OneToMany(mappedBy = "vehicule")
    private List<Contratlocation> contratlocations;

    @OneToMany(mappedBy = "vehicule")
    private List<DisponibiliteVehicule> disponibilites; // <-- relation vers disponibilités

    public String getTypeVehicule() {
        return this.getClass().getSimpleName();
    }
}
