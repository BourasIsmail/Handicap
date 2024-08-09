package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.Specialite;
import ma.entraide.handicap.Repository.SpecialiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialiteService {

    @Autowired
    private SpecialiteRepo specialiteRepo;

    public List<Specialite> getSpecialite() {
        return specialiteRepo.findAll();
    }

    public Page<Specialite> getAllSpecialite(Pageable pageable) {
        return specialiteRepo.findAll(pageable);
    }

    public Specialite getSpecialiteById(Long id) {
        Optional<Specialite> specialite = specialiteRepo.findById(id);
        if (specialite.isPresent()) {
            return specialite.get();
        }
        else {
            throw new ResourceNotFoundException("Specialite not found");
        }
    }

    public Specialite addSpecialite(Specialite specialite) {
        return specialiteRepo.save(specialite);
    }

    public Specialite updateSpecialite(Long id, Specialite specialite) {
        Specialite newSpecialite = getSpecialiteById(id);
        newSpecialite.setSpecialite(specialite.getSpecialite());
        return specialiteRepo.save(newSpecialite);
    }

    public String deleteSpecialite(Long id) {
        specialiteRepo.deleteById(id);
        return "Specialite deleted";
    }
}
