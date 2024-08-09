package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepo extends JpaRepository<Association, Long> {

    @Query("select d from Association d where d.deleguation.name = :delegation")
    public List<Association> getAssociationsByDelegation(@Param("delegation") String delegation);

    @Query("select count(d) from Association d")
    public int countAssociations();

    @Query("select count(d) from Etablissement d")
    public int countEtablissements();

    @Query("select count(d) from Beneficiaire d")
    public int countBeneficiaries();

    @Query("select count(d) from Beneficiaire d where d.programme.programmeName = :programme")
    public int countBeneficiariesProgramme(@Param("programme") String programme);

    @Query("select count(d) from Beneficiaire d where d.sexe = :sexe")
    public int countBeneficiariesSexe(@Param("sexe") String sexe);

    @Query("select count(d) from Beneficiaire d where d.typeHandicap.handicap = :handicap")
    public int countBeneficiariesHandicap(@Param("handicap") String handicap);

    @Query("select count(d) from Beneficiaire d where :service member of d.services")
    public int countBeneficiariesContaines(@Param("service") String service);
}
