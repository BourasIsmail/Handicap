package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, Long> ,
        PagingAndSortingRepository<Etablissement, Long> {

}
