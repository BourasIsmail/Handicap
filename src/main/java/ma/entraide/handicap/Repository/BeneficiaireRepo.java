package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Association;
import ma.entraide.handicap.Entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaireRepo extends JpaRepository<Beneficiaire, Long> {

    @Query("select d from Beneficiaire d where d.province.name = :province order by d.association.name")
    public List<Beneficiaire> getBenefByProvince(@Param("province") String province);

    @Query("select d from Beneficiaire d order by d.province.region.name, d.province.name, d.association.name")
    public List<Beneficiaire> getAllBenef();
}
