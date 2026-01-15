    package com.example.gestion_location_vehicule.model;


    import java.util.List;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @DiscriminatorColumn(
            name = "DTYPE",
            discriminatorType = DiscriminatorType.STRING,
            length = 20
    )
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Utilisateur {
        @Id
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "utilisateur_seq"
        )
        @SequenceGenerator(
                name = "utilisateur_seq",
                sequenceName = "UTILISATEUR_SEQ",
                allocationSize = 1
        )
        private Long id;

        @Column(unique = true, nullable = false)
        private String username; //nom pour se connecter

        @Column(nullable = false)
        private String mdp;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String telephone;
        private String ville;

        // information général pour noter
        private double notemoyenne = 0.0; // recevoir
        private int nombreevaluations = 0; // recevoir

        //messages
        @OneToMany(mappedBy = "utilisateurreceive")
        @JsonIgnore
        private List<Message> messagerecus;

        // Messages envoyés → l'utilisateur est l'expéditeur
        @OneToMany(mappedBy = "utilisateursend")
        @JsonIgnore
        private List<Message> messagesenvoyes;

        @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private PorteMonnaie porteMonnaie;

        public String getTypeUtilisateur() {
            return this.getClass().getSimpleName();
        }
    }
