package ma.entraide.handicap.Controller;


import ma.entraide.handicap.Entity.Province;
import ma.entraide.handicap.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/all")
    public ResponseEntity<List<Province>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        return ResponseEntity.ok(provinces);
    }

    @GetMapping
    public ResponseEntity<Page<Province>> getProvinces(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size,
                                                       @RequestParam(defaultValue = "name") String sortField,
                                                       @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Province> pages = provinceService.getAllPaginatedProvinces(pageable);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getProvinceById(@PathVariable Long id) {
        try {
            Province province = provinceService.getProvinceById(id);
            return ResponseEntity.ok(province);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<List<Province>> getProvinceByRegionId(@PathVariable Long id) {
        try {
            List<Province> provinces = provinceService.getProvinceByRegion(id);
            return ResponseEntity.ok(provinces);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Province> addProvince(@RequestBody Province province) {
        try {
            Province newProvince = provinceService.createProvince(province);
            return ResponseEntity.ok(newProvince);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Province> updateProvince(@PathVariable Long id, @RequestBody Province province) {
        try {
            Province updatedProvince = provinceService.updateProvince(id, province);
            return ResponseEntity.ok(updatedProvince);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProvince(@PathVariable Long id) {
        try {
            provinceService.deleteProvince(id);
            return ResponseEntity.ok("Province supprimée");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
