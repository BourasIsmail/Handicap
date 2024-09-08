package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Entity.Demande;
import ma.entraide.handicap.Service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demande")
@CrossOrigin("*")
public class DemandeController {

    @Autowired
    DemandeService demandeService;

    @PostMapping("/add")
    public ResponseEntity<Demande> addBeneficiaire(@RequestBody Demande demande) {
        try {
            Demande result = demandeService.addDemande(demande);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/downloadDemande/{id}")
    public ResponseEntity<Resource> downloadFileDemande(@PathVariable Long id) {
        Demande demande = null;
        demande = demandeService.getDemandeById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demande.getPdfFileName() + "\"")
                .body(new ByteArrayResource(demande.getPdfFile()));
    }

}
