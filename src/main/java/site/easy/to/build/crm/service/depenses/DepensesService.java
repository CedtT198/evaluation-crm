package site.easy.to.build.crm.service.depenses;

import java.util.List;
import site.easy.to.build.crm.entity.Depenses;

public interface DepensesService {
    List<Depenses> findAllDepensesPerCustomer();
    Depenses findById(Integer id);
    List<Depenses> findAllNotConfirm(Integer customerId);
}
