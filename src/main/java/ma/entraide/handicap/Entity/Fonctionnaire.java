package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fonctionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fonctionnaire_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "association_id")
    private Association association;

    private String fullName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;

    private double salaireMensuel;

    //صيغة التعاقد
    private String contrat;

    //"التصريح بالصندوق الوطني
    // للضمان الاجتماعي CNSS"
    private String cnss;

    private String fraisCnss;

    private double montantAnnuel;

    private Date dateCreation;

    private Date dateModification;

}
