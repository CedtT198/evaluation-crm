package site.easy.to.build.crm.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    List<Budget> findByCustomerCustomerId(Integer customerId);

    @Query("SELECT SUM(b.amount) FROM Budget b WHERE b.customer.id = :idCustomer")
    BigDecimal getTotalAmount(Integer idCustomer);
}
