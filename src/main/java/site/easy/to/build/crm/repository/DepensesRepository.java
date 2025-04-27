package site.easy.to.build.crm.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.easy.to.build.crm.entity.Depenses;

public interface DepensesRepository extends JpaRepository<Depenses, Integer> {
    @Query("SELECT d FROM Depenses d WHERE d.confirm=true ORDER BY d.customerId, d.dateDepense DESC")
    List<Depenses> findAllDepensesPerCustomer();
    
    // @Query("SELECT d.customerId, COALESCE(SUM(d.amount), 0) from Depenses d WHERE d.confirm=true AND d.dateDepense BETWEEN :start AND :end group by d.customerId order by d.dateDepense")
    // List<Depenses> findAllGroupByCustomer(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    // @Query("""
    //     SELECT null as d.id, d.customerId, COALESCE(SUM(d.amount), 0) as d.amount, null as d.dateDepense,
    //     null as d.source, null as d.referenceId, null as d.description, null as d.summary, null as d.confirm
    //     from Depenses d WHERE d.confirm=true group by d.customerId 
    // """)
    // List<Depenses> findAllGroupByCustomer();
    
    @Query("SELECT d FROM Depenses d WHERE d.confirm=false AND d.dateDepense BETWEEN :start AND :end ORDER BY d.customerId, d.dateDepense DESC")
    List<Depenses> findAllDepensesPerCustomerFilteredByDate(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    @Query("SELECT d FROM Depenses d WHERE d.confirm=true AND d.customerId= :idCustomer ORDER BY d.dateDepense DESC")
    List<Depenses> findAllNotConfirmed(@Param("idCustomer") Integer idCustomer);
}
