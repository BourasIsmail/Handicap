package ma.entraide.handicap.Repository;

import ma.entraide.handicap.Entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepo extends JpaRepository<Programme, Long>,
        PagingAndSortingRepository<Programme, Long>
{

}
