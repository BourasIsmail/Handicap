package ma.entraide.handicap.Controller;

import ma.entraide.handicap.Entity.Beneficiaire;
import ma.entraide.handicap.Entity.Demande;
import ma.entraide.handicap.Service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

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

    @GetMapping("/downloadQr/{id}")
    public ResponseEntity<InputStreamResource> downloadQr(@PathVariable Long id) throws Exception {
        Demande demande = demandeService.getDemandeById(id);

        // Assuming demande.getQrCode() returns the byte[] of the QR code
        byte[] qrCodeBytes = demande.getQrCode();

        // Convert byte[] back to BufferedImage
        InputStream inputStream = new ByteArrayInputStream(qrCodeBytes);
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        // Prepare the output stream to write the BufferedImage in PNG format
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);

        // Create an InputStreamResource from the ByteArrayOutputStream
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));

        // Return as ResponseEntity with the correct headers
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demande.getQrCodeFileName() + "\"")
                .body(inputStreamResource);
    }

}
