package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.Programme;
import ma.entraide.handicap.Repository.ProgrammeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepo programmeRepo;

    public Page<Programme> getAllProgrammes(Pageable pageable) {
        return programmeRepo.findAll(pageable);
    }

    public List<Programme> getProgrammes() {
        return programmeRepo.findAll();
    }

    public Programme getProgrammeById(Long id) {
        Optional<Programme> programme = programmeRepo.findById(id);
        if(programme.isPresent()) {
            return programme.get();
        }
        else {
            throw new ResourceNotFoundException("programme not found");
        }
    }

    public Programme addProgramme(Programme programme) {
        return programmeRepo.save(programme);
    }

    public Programme updateProgramme(Long id, Programme programme) {
        Programme newProgramme = getProgrammeById(id);
        newProgramme.setProgrammeName(programme.getProgrammeName());
        return programmeRepo.save(newProgramme);
    }

    public String deleteProgramme(Long id) {
        programmeRepo.deleteById(id);
        return "Programme deleted";
    }

}
