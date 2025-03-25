package site.easy.to.build.crm.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    List<Budget> findByCustomerCustomerId(Integer customerId);

    @Query("SELECT COALESCE(SUM(b.amount), 0) FROM Budget b WHERE b.customer.id = :idCustomer AND b.dateBudget <= :date")
    BigDecimal getTotalAmount(@Param("idCustomer") Integer idCustomer, @Param("date") LocalDate date); 
}
