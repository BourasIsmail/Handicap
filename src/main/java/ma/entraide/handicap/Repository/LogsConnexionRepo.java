package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.LogsConnexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsConnexionRepo extends JpaRepository<LogsConnexion, Long> {



}
