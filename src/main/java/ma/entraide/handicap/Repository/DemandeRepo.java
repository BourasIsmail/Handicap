package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepo extends JpaRepository<Demande, Long> {

}
