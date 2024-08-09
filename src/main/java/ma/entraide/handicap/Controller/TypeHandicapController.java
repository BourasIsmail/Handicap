package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Specialite;
import ma.entraide.handicap.Entity.TypeHandicap;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.TypeHandicapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TypeHandicap")
@CrossOrigin("*")
public class TypeHandicapController {
    @Autowired
    private TypeHandicapService typeHandicapService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeHandicap>> getAll() {
        List<TypeHandicap> typeHandicaps =  typeHandicapService.getTypeHandicap();
        return ResponseEntity.ok(typeHandicaps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeHandicap> getById(@PathVariable Long id) {
        try {
            TypeHandicap typeHandicap = typeHandicapService.getTypeHandicapById(id);
            return ResponseEntity.ok(typeHandicap);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<TypeHandicap> add(@RequestBody TypeHandicap typeHandicap) {
        try {
            TypeHandicap result = typeHandicapService.addTypeHandicap(typeHandicap);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TypeHandicap> update(@PathVariable Long id, @RequestBody TypeHandicap update) {
        try {
            TypeHandicap result = typeHandicapService.updateTypeHandicap(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = typeHandicapService.deleteTypeHandicap(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting service: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }
}
