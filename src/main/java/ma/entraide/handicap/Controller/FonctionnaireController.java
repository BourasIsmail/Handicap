package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Entity.Fonctionnaire;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.FonctionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonctionnaire")
@CrossOrigin("*")
public class FonctionnaireController {
    @Autowired
    private FonctionnaireService fonctionnaireService;

    @GetMapping("/all")
    public ResponseEntity<List<Fonctionnaire>> getAllFonctionnaires() {
        List<Fonctionnaire> fonctionnaires =  fonctionnaireService.getAllFonctionnaire();
        return ResponseEntity.ok(fonctionnaires);
    }

    @GetMapping("/ByDelegation/{delegation}")
    public ResponseEntity<List<Fonctionnaire>> getFonctionnairesByDelegation(@PathVariable String delegation) {
        List<Fonctionnaire> fonctionnaires = fonctionnaireService.getAllFonctionnaireByProvince(delegation);
        return ResponseEntity.ok(fonctionnaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fonctionnaire> getFonctionnaire(@PathVariable Long id) {
        try {
            Fonctionnaire fonctionnaire = fonctionnaireService.getFonctionnaireById(id);
            return ResponseEntity.ok(fonctionnaire);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Fonctionnaire> add(@RequestBody Fonctionnaire fonctionnaire) {
        try {
            Fonctionnaire result = fonctionnaireService.addFonctionnaire(fonctionnaire);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fonctionnaire> update(@PathVariable Long id, @RequestBody Fonctionnaire update) {
        try {
            Fonctionnaire result = fonctionnaireService.updateFonctionnaire(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = fonctionnaireService.deleteFonctionnaire(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting fonctionnaire: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }

}
