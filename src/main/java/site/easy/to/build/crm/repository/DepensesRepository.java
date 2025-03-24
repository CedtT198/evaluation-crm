package site.easy.to.build.crm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.easy.to.build.crm.entity.Depenses;

public interface DepensesRepository extends JpaRepository<Depenses, Integer> {
    @Query("SELECT d FROM Depenses d WHERE d.confirm=false ORDER BY d.customerId, d.dateDepense DESC")
    List<Depenses> findAllDepensesPerCustomer();
    
    @Query("SELECT d FROM Depenses d WHERE d.confirm=true AND d.customerId= :idCustomer ORDER BY d.dateDepense DESC")
    List<Depenses> findAllNotConfirmed(@Param("idCustomer") Integer idCustomer);
}
