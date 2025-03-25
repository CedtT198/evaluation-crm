package site.easy.to.build.crm.repository;

import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.DepensesTicket;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DepensesTicketRepository extends JpaRepository<DepensesTicket, Integer> {
    @Query("SELECT d from DepensesTicket d WHERE d.confirm=true order by d.dateDepense")
    List<DepensesTicket> findAllOrderByDate();
    
    @Query("SELECT d.ticket.customer.customerId, COALESCE(SUM(d.amount), 0) from DepensesTicket d WHERE d.confirm=true AND d.dateDepense BETWEEN :start AND :end group by d.ticket.customer.id order by d.dateDepense")
    List<DepensesTicket> findAllGroupByCustomer(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT d from DepensesTicket d WHERE d.confirm=true AND d.dateDepense BETWEEN :start AND :end order by d.dateDepense")
    List<DepensesTicket> findAllOrderAndFilteredByDate(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT d FROM DepensesTicket d WHERE d.ticket.customer.id = :idCustomer")
    List<DepensesTicket> findByCustomerId(@Param("idCustomer") Integer customerId);

    @Query("SELECT COALESCE(SUM(dl.amount), 0) FROM DepensesTicket dl WHERE dl.ticket.customer.id = :idCustomer AND dl.dateDepense <= :date AND dl.confirm=true")
    BigDecimal getTotalDepensesAmount(@Param("idCustomer") Integer idCustomer, @Param("date") LocalDate date); 
}
