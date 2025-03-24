package site.easy.to.build.crm.service.depenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.repository.DepensesLeadRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepensesLeadServiceImpl implements DepensesLeadService {

    @Autowired
    private DepensesLeadRepository depensesLeadRepository;

    @Override
    public List<DepensesLead> findAllOrderByDate() {
        return depensesLeadRepository.findAllOrderByDate();
    }

    @Override
    public DepensesLead update(DepensesLead depensesLead) {
        return depensesLeadRepository.save(depensesLead);
    }

    @Override
    public void delete(Integer id) {
        depensesLeadRepository.deleteById(id);
    }

    @Override
    public DepensesLead save(DepensesLead depensesLead) throws Exception {
        if (depensesLead.getAmount().intValue() < 0) throw new Exception("Amount cannot be under 0.");
        return depensesLeadRepository.save(depensesLead);
    }
    
    @Override
    public List<DepensesLead> findByCustomerId(Integer customerId) {
        return depensesLeadRepository.findByCustomerId(customerId);
    }
    
    @Override
    public BigDecimal getTotalAmount(Integer idCustomer, LocalDate date) {
        return depensesLeadRepository.getTotalDepensesAmount(idCustomer, date);
    }

    @Override
    public DepensesLead findById(Integer id) {
        return depensesLeadRepository.findById(id).get();
    }


}
