package ma.entraide.handicap.Service;


import ma.entraide.handicap.Entity.Province;
import ma.entraide.handicap.Repository.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepo provinceRepo;

    public List<Province> getAllProvinces() {
        return provinceRepo.findAll();
    }

    public Page<Province> getAllPaginatedProvinces(Pageable pageable) {
        return provinceRepo.findAll(pageable);
    }

    public Province getProvinceById(Long id) {
        Optional<Province> province = provinceRepo.findById(id);
        if(province.isPresent()) {
            return province.get();
        }
        else {
            throw new ResourceNotFoundException("Province introuvable");
        }
    }

    public Province createProvince(Province province) {
        return provinceRepo.save(province);
    }

    public Province updateProvince(Long id, Province province) {
        Province updatedProvince = provinceRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("province introuvable !"));
        updatedProvince.setName(province.getName());
        updatedProvince.setRegion(province.getRegion());
        return provinceRepo.save(updatedProvince);
    }

    public void deleteProvince(Long id) {
        Province province = getProvinceById(id);
        provinceRepo.delete(province);
    }

    public List<Province> getProvinceByRegion(Long region) {
        return provinceRepo.findByRegionId(region);
    }

}
