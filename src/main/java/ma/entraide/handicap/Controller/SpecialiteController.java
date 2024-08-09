package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.ServiceOffert;
import ma.entraide.handicap.Entity.Specialite;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Specialite")
@CrossOrigin("*")
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping("/all")
    public ResponseEntity<List<Specialite>> getAll() {
        List<Specialite> specialites =  specialiteService.getSpecialite();
        return ResponseEntity.ok(specialites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialite> getById(@PathVariable Long id) {
        try {
            Specialite specialite = specialiteService.getSpecialiteById(id);
            return ResponseEntity.ok(specialite);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Specialite> add(@RequestBody Specialite specialite) {
        try {
            Specialite result = specialiteService.addSpecialite(specialite);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Specialite> update(@PathVariable Long id, @RequestBody Specialite update) {
        try {
            Specialite result = specialiteService.updateSpecialite(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = specialiteService.deleteSpecialite(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting service: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }
}
