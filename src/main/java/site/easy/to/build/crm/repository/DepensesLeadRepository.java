package site.easy.to.build.crm.repository;

import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.DepensesLead;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DepensesLeadRepository extends JpaRepository<DepensesLead, Integer> {
    @Query("SELECT d from DepensesLead d WHERE d.confirm=true order by d.dateDepense")
    List<DepensesLead> findAllOrderByDate();
     
    // @Query("SELECT d.lead.customer.customerId, COALESCE(SUM(d.amount), 0) from DepensesLead d WHERE d.confirm=true AND d.dateDepense BETWEEN :start AND :end group by d.lead.customer.id order by d.dateDepense")
    // List<DepensesLead> findAllGroupByCustomer(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    @Query("SELECT d from DepensesLead d WHERE d.confirm=true AND d.dateDepense BETWEEN :start AND :end order by d.dateDepense")
    List<DepensesLead> findAllOrderAndFilteredByDate(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT d FROM DepensesLead d WHERE d.lead.customer.id = :idCustomer")
    List<DepensesLead> findByCustomerId(@Param("idCustomer") Integer customerId);
 
    @Query("SELECT COALESCE(SUM(dl.amount), 0) FROM DepensesLead dl WHERE dl.lead.customer.id = :idCustomer AND dl.dateDepense <= :date AND dl.confirm=true")
    BigDecimal getTotalDepensesAmount(@Param("idCustomer") Integer idCustomer, @Param("date") LocalDate date); 
}
