package site.easy.to.build.crm.service.depenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Depenses;
import site.easy.to.build.crm.repository.DepensesRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepensesServiceImpl implements DepensesService {
    
    @Autowired
    private DepensesRepository depensesRepository;
    
    // @Override
    // public List<Depenses> findAllGroupByCustomer(LocalDate start, LocalDate end) {
    //     return depensesRepository.findAllGroupByCustomer(start, end);
    // }
    
    // @Override
    // public List<Depenses> findAllGroupByCustomer() {
    //     return depensesRepository.findAllGroupByCustomer();
    // }

    @Override
    public List<Depenses> findAllDepensesPerCustomer() {
        return depensesRepository.findAllDepensesPerCustomer();
    }

    @Override
    public List<Depenses> findAllNotConfirm(Integer customerId) {
        return depensesRepository.findAllNotConfirmed(customerId);
    }

    @Override
    public Depenses findById(Integer id) {
        return depensesRepository.findById(id).get();
    }
}
