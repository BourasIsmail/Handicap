package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.TypeHandicap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeHandicapRepo extends JpaRepository<TypeHandicap, Long> {
}
