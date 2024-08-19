package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Entity.Fonctionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FonctionnaireRepo extends JpaRepository<Fonctionnaire, Long> {

    @Query("select d from Fonctionnaire d where d.province.name = :province order by d.association.name")
    public List<Fonctionnaire> getFoncByProvince(@Param("province") String province);

    @Query("select d from Fonctionnaire d order by d.province.region.name,d.province.name, d.association.name")
    public List<Fonctionnaire> getAllFonctionnaire();
}
