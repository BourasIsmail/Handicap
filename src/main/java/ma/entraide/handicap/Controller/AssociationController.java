package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Association;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/association")
@CrossOrigin("*")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @GetMapping("/all")
    public ResponseEntity<List<Association>> getAllAssociations() {
        List<Association> associations =  associationService.getAllAssociations();
        return ResponseEntity.ok(associations);
    }

    @GetMapping("/ByDelegation/{delegation}")
    public ResponseEntity<List<Association>> getAssociationsByDelegation(@PathVariable String delegation) {
        List<Association> associations = associationService.getAssociationsByProvince(delegation);
        return ResponseEntity.ok(associations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Association> getAssociation(@PathVariable Long id) {
        try {
            Association association = associationService.getAssociationById(id);
            return ResponseEntity.ok(association);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addAssociation")
    public ResponseEntity<Association> addAssociation(@RequestBody Association association) {
        try {
            Association result = associationService.createAssociation(association);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Association update) {
        try {
            String result = associationService.updateAssociation(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = associationService.deleteAssociation(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting association: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Object> getDashboard() {
        return ResponseEntity.ok(associationService.dashboard());
    }

}
