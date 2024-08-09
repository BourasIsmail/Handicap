package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beneficiaire_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "association_id")
    private Association association;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "programme_id")
    private Programme programme;

    private String fullName;

    private int age;

    private String sexe;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "handicap_id")
    private TypeHandicap typeHandicap;

    private String degreHandicap;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    @OneToMany(cascade = CascadeType.DETACH)
    //@JoinColumn(name = "service_id")
    private List<ServiceOffert> services;




}
