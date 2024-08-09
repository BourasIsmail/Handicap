package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.Etablissement;
import ma.entraide.handicap.Repository.EtablissementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    private EtablissementRepo etablissementRepo;

    public List<Etablissement> getEtablissements() {
        return etablissementRepo.findAll();
    }

    public Page<Etablissement> getAllEtablissement(Pageable pageable) {
        return etablissementRepo.findAll(pageable);
    }

    public Etablissement getEtablissementById(Long id) {
        Optional<Etablissement> etablissement = etablissementRepo.findById(id);
        if (etablissement.isPresent()) {
            return etablissement.get();
        }
        else {
            throw new ResourceNotFoundException("etablissement not found");
        }
    }

    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissementRepo.save(etablissement);
    }

    public Etablissement updateEtablissement(Long id,Etablissement etablissement) {
        Etablissement newEtablissement = getEtablissementById(id);
        newEtablissement.setName(etablissement.getName());
        return etablissementRepo.save(newEtablissement);
    }

    public String deleteEtablissement(Long id) {
        etablissementRepo.deleteById(id);
        return "supprimé";
    }
}
