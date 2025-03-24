package site.easy.to.build.crm.service.depenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.repository.DepensesTicketRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepensesTicketServiceImpl implements DepensesTicketService {

    @Autowired
    private DepensesTicketRepository depensesTicketRepository;
    
    @Override
    public DepensesTicket findById(Integer id) {
        return depensesTicketRepository.findById(id).get();
    }

    @Override
    public List<DepensesTicket> findAllOrderByDate() {
        return depensesTicketRepository.findAllOrderByDate();
    }

    @Override
    public DepensesTicket update(DepensesTicket depensesTicket) {
        return depensesTicketRepository.save(depensesTicket);
    }

    @Override
    public void delete(Integer id) {
        depensesTicketRepository.deleteById(id);
    }

    @Override
    public DepensesTicket save(DepensesTicket depensesTicket) throws Exception {
        if (depensesTicket.getAmount().intValue() < 0) throw new Exception("Amount cannot be under 0.");
        return depensesTicketRepository.save(depensesTicket);
    }
    
    @Override
    public List<DepensesTicket> findByCustomerId(Integer customerId) {
        return depensesTicketRepository.findByCustomerId(customerId);
    }
    
    @Override
    public BigDecimal getTotalAmount(Integer idCustomer, LocalDate date) {
        return depensesTicketRepository.getTotalDepensesAmount(idCustomer, date);
    }

}
