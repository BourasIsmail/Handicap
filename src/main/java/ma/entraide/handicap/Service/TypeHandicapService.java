package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.TypeHandicap;
import ma.entraide.handicap.Repository.TypeHandicapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeHandicapService {

    @Autowired
    private TypeHandicapRepo typeHandicapRepo;

    public List<TypeHandicap> getTypeHandicap() {
        return typeHandicapRepo.findAll();
    }

    public Page<TypeHandicap> getAllTypeHandicap(Pageable pageable) {
        return typeHandicapRepo.findAll(pageable);
    }

    public TypeHandicap getTypeHandicapById(Long id) {
        Optional<TypeHandicap> typehandicap = typeHandicapRepo.findById(id);
        if (typehandicap.isPresent()) {
            return typehandicap.get();
        }
        else {
            throw new ResourceNotFoundException("TypeHandicap not found");
        }
    }

    public TypeHandicap addTypeHandicap(TypeHandicap typeHandicap) {
        return typeHandicapRepo.save(typeHandicap);
    }

    public TypeHandicap updateTypeHandicap(Long id, TypeHandicap typeHandicap) {
        TypeHandicap newTypeHandicap = getTypeHandicapById(id);
        newTypeHandicap.setHandicap(typeHandicap.getHandicap());
        return typeHandicapRepo.save(newTypeHandicap);
    }

    public String deleteTypeHandicap(Long id) {
        typeHandicapRepo.deleteById(id);
        return "handicap deleted";
    }
}
