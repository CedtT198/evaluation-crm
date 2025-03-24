package site.easy.to.build.crm.service.depenses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import site.easy.to.build.crm.entity.DepensesLead;

public interface DepensesLeadService {
    DepensesLead findById(Integer id);
    DepensesLead update(DepensesLead depensesLead);
    void delete(Integer id);
    List<DepensesLead> findAllOrderByDate();
    BigDecimal getTotalAmount(Integer idCustomer, LocalDate date);
    List<DepensesLead> findByCustomerId(Integer customerId);
    DepensesLead save(DepensesLead depenses) throws Exception;
}
