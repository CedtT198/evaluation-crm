package site.easy.to.build.crm.service.depenses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import site.easy.to.build.crm.entity.DepensesTicket;

public interface DepensesTicketService {
    DepensesTicket findById(Integer id);
    DepensesTicket update(DepensesTicket depensesTicket);
    void delete(Integer id);
    List<DepensesTicket> findAllOrderByDate();
    DepensesTicket save(DepensesTicket depenses) throws Exception ;
    List<DepensesTicket> findByCustomerId(Integer customerId);
    BigDecimal getTotalAmount(Integer idCustomer, LocalDate date);
}
