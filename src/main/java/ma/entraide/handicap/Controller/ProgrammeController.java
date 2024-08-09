package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Etablissement;
import ma.entraide.handicap.Entity.Programme;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Programme")
@CrossOrigin("*")
public class ProgrammeController {

    @Autowired
    private ProgrammeService programmeService;

    @GetMapping("/all")
    public ResponseEntity<List<Programme>> getAll() {
        List<Programme> etablissements =  programmeService.getProgrammes();
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programme> getById(@PathVariable Long id) {
        try {
            Programme programme = programmeService.getProgrammeById(id);
            return ResponseEntity.ok(programme);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Programme> add(@RequestBody Programme programme) {
        try {
            Programme result = programmeService.addProgramme(programme);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Programme> update(@PathVariable Long id, @RequestBody Programme update) {
        try {
            Programme result = programmeService.updateProgramme(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = programmeService.deleteProgramme(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting etablissement: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }

}
