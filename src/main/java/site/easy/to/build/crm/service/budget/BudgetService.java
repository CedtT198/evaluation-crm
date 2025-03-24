package site.easy.to.build.crm.service.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;

public interface BudgetService {
    public boolean isOverBudget(BigDecimal expense, LocalDate date, Customer idCustomer) throws Exception ;
    public boolean isLimitReached(BigDecimal expense, LocalDate date, Customer idCustomer) throws Exception ;
    BigDecimal getLimit(Integer idCustomer);
    Budget save(Budget budget) throws Exception ;
    List<Budget> findByCustomerId(Integer customerId);
}
