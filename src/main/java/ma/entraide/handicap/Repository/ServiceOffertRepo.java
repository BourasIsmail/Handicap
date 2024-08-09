package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.ServiceOffert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOffertRepo extends JpaRepository<ServiceOffert, Long> {
}
