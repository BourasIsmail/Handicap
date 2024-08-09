package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Entity.Etablissement;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Etablissement")
@CrossOrigin("*")
public class EtablissementController {

    @Autowired
    private EtablissementService etabissementService;


    @GetMapping("/all")
    public ResponseEntity<List<Etablissement>> getAll() {
        List<Etablissement> etablissements =  etabissementService.getEtablissements();
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getEtablissement(@PathVariable Long id) {
        try {
            Etablissement etablissement = etabissementService.getEtablissementById(id);
            return ResponseEntity.ok(etablissement);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Etablissement> addBeneficiaire(@RequestBody Etablissement etablissement) {
        try {
            Etablissement result = etabissementService.addEtablissement(etablissement);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Etablissement> update(@PathVariable Long id, @RequestBody Etablissement update) {
        try {
            Etablissement result = etabissementService.updateEtablissement(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = etabissementService.deleteEtablissement(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting etablissement: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }
}
