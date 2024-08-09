package ma.entraide.handicap.Service;


import ma.entraide.handicap.Entity.Region;
import ma.entraide.handicap.Repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepo regionRepo;

    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

    public Page<Region> getPaginatedRegions(Pageable pageable) {
        return regionRepo.findAll(pageable);
    }

    public Region getRegionById(Long id) {
        Optional<Region> regionOpt = regionRepo.findById(id);
        if (regionOpt.isPresent()) {
            return regionOpt.get();
        }
        else {
            throw  new ResourceNotFoundException("region inexistante ou introuvable");
        }
    }

    public Region saveRegion(Region region) {
        return regionRepo.save(region);
    }

    public Region updateRegion(Long id,Region region) {
        Region updatedRegion = regionRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Region intouvable !"));
        updatedRegion.setName(region.getName());
        return regionRepo.save(updatedRegion);
    }

    public String deleteRegion(Long id) {
        Region region = getRegionById(id);
        regionRepo.delete(region);
        return "Region supprimée";
    }


}
