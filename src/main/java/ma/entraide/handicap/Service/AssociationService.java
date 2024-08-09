package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.*;
import ma.entraide.handicap.Repository.AssociationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepo associationRepo;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private EtablissementService etablissementService;

    public List<Association> getAllAssociations() {
        return associationRepo.findAll();
    }

    public List<Association> getAssociationsByProvince(String province) {
        return associationRepo.getAssociationsByDelegation(province);
    }

    public Association getAssociationById(Long id) {
        Optional<Association> association = associationRepo.findById(id);
        if(association.isPresent()) {
            return association.get();
        }
        else{
            throw new ResourceNotFoundException("Association not found");
        }
    }

    public Association createAssociation(Association association) {
        Province province =provinceService.getProvinceById(association.getDeleguation().getId());
        association.setDeleguation(province);
        Programme programme = programmeService.getProgrammeById(association.getProgramme().getId());
        association.setProgramme(programme);
        //etablissement
        List<Etablissement> etablissementsOpt = association.getEtablissements();
        List<Etablissement> etablissements = new ArrayList<Etablissement>();
        for(Etablissement etablissement : etablissementsOpt) {
            Etablissement etablissement1 = etablissementService.getEtablissementById(etablissement.getId());
            etablissements.add(etablissement1);
        }
        association.setEtablissements(etablissements);

        return associationRepo.save(association);
    }

    public String updateAssociation(Long id, Association association) {
        Association newAssociation = getAssociationById(id);
        Province province =provinceService.getProvinceById(association.getDeleguation().getId());
        newAssociation.setDeleguation(province);
        Programme programme = programmeService.getProgrammeById(association.getProgramme().getId());
        newAssociation.setProgramme(programme);
        //etablissement
        List<Etablissement> etablissementsOpt = association.getEtablissements();
        List<Etablissement> etablissements = new ArrayList<Etablissement>();
        for(Etablissement etablissement : etablissementsOpt) {
            Etablissement etablissement1 = etablissementService.getEtablissementById(etablissement.getId());
            etablissements.add(etablissement1);
        }
        newAssociation.setEtablissements(etablissements);
        newAssociation.setEmail(association.getEmail());
        newAssociation.setName(association.getName());
        newAssociation.setFullName(association.getFullName());
        newAssociation.setAdresse(association.getAdresse());
        newAssociation.setEmploiSelonAnnee(association.getEmploiSelonAnnee());
        newAssociation.setTelephone(association.getTelephone());
         associationRepo.save(newAssociation);
         return "association mis a jour";
    }

    public String deleteAssociation(Long id) {
        Association association = getAssociationById(id);
        association.setDeleguation(null);
        association.setProgramme(null);
        association.setEtablissements(null);
        associationRepo.delete(association);
        return "association supprime";
    }

    public Object dashboard(){
        Object data = new Object(){
           public int nbrAssociations = associationRepo.countAssociations();
           public int nbrEtablissements = associationRepo.countEtablissements();
           public int nbrBeneficiaries = associationRepo.countBeneficiaries();
           public int nbrBeneficiairesParProgrammeA = associationRepo.countBeneficiariesProgramme("A");
           public int getNbrBeneficiairesParProgrammeB = associationRepo.countBeneficiariesProgramme("B");
           public int getNbrBeneficiairesParProgrammeC = associationRepo.countBeneficiariesProgramme("C");
           public int getnbrHandicapMental = associationRepo.countBeneficiariesHandicap("الإعاقة الذهنية");
           public int getNbrAutisme = associationRepo.countBeneficiariesHandicap("التوحد");
           public int getNbrTrisomie = associationRepo.countBeneficiariesHandicap("التثلث الصبغي");
           public int getNbrIMC = associationRepo.countBeneficiariesHandicap("IMC الشلل الدماغي");
           public int getNbrIMOC = associationRepo.countBeneficiariesHandicap("IMOC الشلل الدماغي");
           public int getNbrHandicapMvmt = associationRepo.countBeneficiariesHandicap("الإعاقة الحركية");
           public int getNbrHandicapAuditif = associationRepo.countBeneficiariesHandicap("الإعاقة السمعية");
           public int getNbrOptique = associationRepo.countBeneficiariesHandicap("الإعاقة البصرية");
           public int getNbrhandicapMultiple = associationRepo.countBeneficiariesHandicap("الإعاقة المتعددة");
           public int getNbrTroubleApprentissage = associationRepo.countBeneficiariesHandicap("اضطرابات التعلم");
           public int getNbrPeauSeche = associationRepo.countBeneficiariesHandicap("جفاف الجلد المصطبغ");
        };
        return data;
    }
}
