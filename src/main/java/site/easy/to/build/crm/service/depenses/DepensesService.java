package site.easy.to.build.crm.service.depenses;

import java.time.LocalDate;
import java.util.List;
import site.easy.to.build.crm.entity.Depenses;

public interface DepensesService {
    // public List<Depenses> findAllGroupByCustomer(LocalDate start, LocalDate end);
    // public List<Depenses> findAllGroupByCustomer();
    List<Depenses> findAllDepensesPerCustomer();
    Depenses findById(Integer id);
    List<Depenses> findAllNotConfirm(Integer customerId);
}
