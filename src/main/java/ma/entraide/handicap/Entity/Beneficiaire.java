package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    private String age;

    private String sexe;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "handicap_id")
    private TypeHandicap typeHandicap;

    private String degreHandicap;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "benef_service",
            joinColumns = @JoinColumn(name = "beneficiaire_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceOffert> services;


    private Date dateCreation;

    private Date dateModification;

}
