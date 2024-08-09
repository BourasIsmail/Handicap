package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Association;
import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaire")
@CrossOrigin("*")
public class BeneficiaireController {
    @Autowired
    BeneficiaireService beneficiaireService;

    @GetMapping("/all")
    public ResponseEntity<List<Beneficiaire>> getAllBeneficiaires() {
        List<Beneficiaire> beneficiaires =  beneficiaireService.getAllBeneficiaire();
        return ResponseEntity.ok(beneficiaires);
    }

    @GetMapping("/ByDelegation/{delegation}")
    public ResponseEntity<List<Beneficiaire>> getBeneficiairesByDelegation(@PathVariable String delegation) {
        List<Beneficiaire> beneficiaires = beneficiaireService.getAllBeneficiaireByProvince(delegation);
        return ResponseEntity.ok(beneficiaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiaire> getBeneficiaire(@PathVariable Long id) {
        try {
            Beneficiaire beneficiaire = beneficiaireService.getBeneficiaireById(id);
            return ResponseEntity.ok(beneficiaire);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Beneficiaire> addBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        try {
            Beneficiaire result = beneficiaireService.addBeneficiaire(beneficiaire);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Beneficiaire> update(@PathVariable Long id, @RequestBody Beneficiaire update) {
        try {
            Beneficiaire result = beneficiaireService.updateBeneficiaire(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = beneficiaireService.deleteBeneficiaire(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting beneficiaire: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }

}
