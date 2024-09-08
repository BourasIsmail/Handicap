package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "association_id")
    private Association association;

    private String nomComplet;

    private String telephone;

    private String email;

    private long nbrProgA;

    private long nbrProgB;

    private long nbrProgC;

    private long totalNbrProg;

    private String sujetDemande;

    private Date dateDemande;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] pdfFile;

    private String pdfFileName;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] qrCode;

    private String qrCodeFileName;
}
