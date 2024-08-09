package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.*;
import ma.entraide.handicap.Repository.BeneficiaireRepo;
import ma.entraide.handicap.Repository.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {

    @Autowired
    private BeneficiaireRepo beneficiaireRepo;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private AssociationService associationService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private TypeHandicapService typeHandicapService;

    @Autowired
    private EtablissementService etablissementService;

    @Autowired
    private ServiceOffertService serviceOffertService;

    public List<Beneficiaire> getAllBeneficiaire() {
        return beneficiaireRepo.findAll();
    }

    public List<Beneficiaire> getAllBeneficiaireByProvince(String province) {
        return beneficiaireRepo.getBenefByProvince(province);
    }

    public Beneficiaire getBeneficiaireById(Long id) {
        Optional<Beneficiaire> beneficiaire = beneficiaireRepo.findById(id);
        if(beneficiaire.isPresent()){
            return beneficiaire.get();
        }
        else{
            throw new ResourceNotFoundException("Beneficiaire id " + id + " n'existe pas");
        }
    }

    public Beneficiaire addBeneficiaire(Beneficiaire beneficiaire) {
        Province province = provinceService.getProvinceById(beneficiaire.getProvince().getId());
        beneficiaire.setProvince(province);

        Association association = associationService.getAssociationById(beneficiaire.getAssociation().getId());
        beneficiaire.setAssociation(association);

        Programme programme = programmeService.getProgrammeById(beneficiaire.getProgramme().getId());
        beneficiaire.setProgramme(programme);

        TypeHandicap typeHandicap = typeHandicapService.getTypeHandicapById(beneficiaire.getTypeHandicap().getId());
        beneficiaire.setTypeHandicap(typeHandicap);

        Etablissement etablissement = etablissementService.getEtablissementById(beneficiaire.getEtablissement().getId());
        beneficiaire.setEtablissement(etablissement);

        List<ServiceOffert> serviceOffertOpt = beneficiaire.getServices();
        List<ServiceOffert> servicesOfferts = new ArrayList<ServiceOffert>();
        for(ServiceOffert serviceOffert : serviceOffertOpt){
            ServiceOffert serviceOffert2 = serviceOffertService.getServiceOffertById(serviceOffert.getId());
            servicesOfferts.add(serviceOffert2);
        }
        beneficiaire.setServices(servicesOfferts);

        return beneficiaireRepo.save(beneficiaire);
    }

    public Beneficiaire updateBeneficiaire(Long id, Beneficiaire newBeneficiaire) {
        Beneficiaire beneficiaire = getBeneficiaireById(id);

        Province province = provinceService.getProvinceById(newBeneficiaire.getProvince().getId());
        beneficiaire.setProvince(province);

        Association association = associationService.getAssociationById(newBeneficiaire.getAssociation().getId());
        beneficiaire.setAssociation(association);

        Programme programme = programmeService.getProgrammeById(newBeneficiaire.getProgramme().getId());
        beneficiaire.setProgramme(programme);

        TypeHandicap typeHandicap = typeHandicapService.getTypeHandicapById(newBeneficiaire.getTypeHandicap().getId());
        beneficiaire.setTypeHandicap(typeHandicap);

        Etablissement etablissement = etablissementService.getEtablissementById(newBeneficiaire.getEtablissement().getId());
        beneficiaire.setEtablissement(etablissement);

        List<ServiceOffert> serviceOffertOpt = newBeneficiaire.getServices();
        List<ServiceOffert> servicesOfferts = new ArrayList<ServiceOffert>();;
        for(ServiceOffert serviceOffert : serviceOffertOpt){
            ServiceOffert serviceOffert2 = serviceOffertService.getServiceOffertById(serviceOffert.getId());
            servicesOfferts.add(serviceOffert2);
        }
        beneficiaire.setServices(servicesOfferts);

        beneficiaire.setFullName(newBeneficiaire.getFullName());
        beneficiaire.setAge(newBeneficiaire.getAge());
        beneficiaire.setSexe(newBeneficiaire.getSexe());
        beneficiaire.setDegreHandicap(newBeneficiaire.getDegreHandicap());

        return beneficiaireRepo.save(beneficiaire);
    }

    public String deleteBeneficiaire(Long id) {
        Beneficiaire beneficiaire = getBeneficiaireById(id);
        beneficiaire.setServices(null);
        beneficiaire.setEtablissement(null);
        beneficiaire.setProvince(null);
        beneficiaire.setTypeHandicap(null);
        beneficiaire.setProgramme(null);

        beneficiaireRepo.delete(beneficiaire);

        return "supprimé";
    }
}
