package ma.entraide.handicap.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import ma.entraide.handicap.Entity.Association;
import ma.entraide.handicap.Entity.Demande;
import ma.entraide.handicap.Entity.Province;
import ma.entraide.handicap.Repository.DemandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepo demandeRepo;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private AssociationService associationService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public Demande addDemande(Demande demande) {
        Province province = provinceService.getProvinceById(demande.getProvince().getId());
        Association association = associationService.getAssociationById(demande.getAssociation().getId());
        demande.setProvince(province);
        demande.setAssociation(association);

        // Generate PDF
        byte[] pdfContent = generatePdf(demande);
        demande.setPdfFile(pdfContent);

        return demandeRepo.save(demande);
    }

    private byte[] generatePdf(Demande demande) {
        // Prepare Thymeleaf context
        Context context = new Context();
        context.setVariable("demande", demande);

        // Render HTML template to string
        String htmlContent = templateEngine.process("demande-template", context);

        // Convert HTML to PDF using OpenPDF
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            com.lowagie.text.html.HtmlParser.parse(document, new java.io.StringReader(htmlContent));
            document.close();
            return outputStream.toByteArray();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
