package ma.entraide.handicap.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import static ma.entraide.handicap.Service.QRCodeGenerator.generateQRCodeImage;
import static ma.entraide.handicap.Service.QRCodeGenerator.getQRCodeImage;

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
        demande.setDateDemande(new Date());
        demande.setTotalNbrProg(demande.getNbrProgA() + demande.getNbrProgB() + demande.getNbrProgC());
        // Generate PDF
        byte[] pdfContent = generatePdf(demande);
        demande.setPdfFile(pdfContent);
        // Generate QR code

        String content = demande.getAssociation().getName()+
                "-"+demande.getProvince().getName()+"-"+demande.getDateDemande().toString()+
                "- A :"+demande.getNbrProgA()+"- B : "+demande.getNbrProgB()+"- C : "
                +demande.getNbrProgC()+ "-"+demande.getTotalNbrProg()+"-"+demande.getSujetDemande()+
                "-"+demande.getNomComplet()+"-"+demande.getTelephone()+"-"+demande.getEmail();

        try {
            demande.setQrCode(generateQRCodeImage(content));
            demande.setQrCodeFileName("QRCode-"+demande.getAssociation().getName()+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public static BufferedImage generateQr(String content) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(content, BarcodeFormat.EAN_13, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public Demande getDemandeById(Long id) {
        return demandeRepo.findById(id).orElse(null);
    }
}
