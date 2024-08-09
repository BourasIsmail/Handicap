package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepo extends JpaRepository<Specialite, Long> {
}
