package ma.entraide.handicap.Service;

import ma.entraide.handicap.Entity.ServiceOffert;
import ma.entraide.handicap.Repository.ServiceOffertRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class ServiceOffertService {

        @Autowired
        private ServiceOffertRepo serviceOffertRepo;

        public List<ServiceOffert> getServiceOffert() {
            return serviceOffertRepo.findAll();
        }

        public Page<ServiceOffert> getAllServiceOffert(Pageable pageable) {
            return serviceOffertRepo.findAll(pageable);
        }

        public ServiceOffert getServiceOffertById(Long id) {
            Optional<ServiceOffert> serviceOffert = serviceOffertRepo.findById(id);
            if (serviceOffert.isPresent()) {
                return serviceOffert.get();
            }
            else {
                throw new ResourceNotFoundException("ServiceOffert not found");
            }
        }

        public ServiceOffert addServiceOffert(ServiceOffert serviceOffert) {
            return serviceOffertRepo.save(serviceOffert);
        }

        public ServiceOffert updateServiceOffert(Long id, ServiceOffert serviceOffert) {
            ServiceOffert newServiceOffert = getServiceOffertById(id);
            newServiceOffert.setServiceName(serviceOffert.getServiceName());
            return serviceOffertRepo.save(newServiceOffert);
        }

        public String  deleteServiceOffert(Long id) {
            serviceOffertRepo.deleteById(id);
            return "Service Offert deleted";
        }
    }
