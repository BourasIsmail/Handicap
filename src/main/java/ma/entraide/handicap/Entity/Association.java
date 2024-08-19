package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "association_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province deleguation;

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "etablissement_id")
    private List<Etablissement> etablissements;

    //اشتغال وفق السنة (الدراسية/ المالية/أخر)
    private String emploiSelonAnnee;

    private String fullName;

    private String telephone;

    private String adresse;

    private String email;
}
