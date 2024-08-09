package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Programme;
import ma.entraide.handicap.Entity.ServiceOffert;
import ma.entraide.handicap.Exceptions.ErrorResponse;
import ma.entraide.handicap.Service.ServiceOffertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Service")
@CrossOrigin("*")
public class ServiceOffertController {
    @Autowired
    private ServiceOffertService serviceOffertService;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceOffert>> getAll() {
        List<ServiceOffert> serviceOfferts =  serviceOffertService.getServiceOffert();
        return ResponseEntity.ok(serviceOfferts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffert> getById(@PathVariable Long id) {
        try {
            ServiceOffert serviceOffert = serviceOffertService.getServiceOffertById(id);
            return ResponseEntity.ok(serviceOffert);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceOffert> add(@RequestBody ServiceOffert serviceOffert) {
        try {
            ServiceOffert result = serviceOffertService.addServiceOffert(serviceOffert);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceOffert> update(@PathVariable Long id, @RequestBody ServiceOffert update) {
        try {
            ServiceOffert result = serviceOffertService.updateServiceOffert(id , update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            String result = serviceOffertService.deleteServiceOffert(id);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            String errorMessage = "An error occurred while deleting service: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }
}
