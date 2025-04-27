package site.easy.to.build.crm.service.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.TauxAlerte;
import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import site.easy.to.build.crm.service.taux.TauxAlerteService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private TauxAlerteService tauxAlerteService;
    @Autowired
    private DepensesLeadService depensesLeadService;
    @Autowired
    private DepensesTicketService depensesTicketService;
    // @Autowired
    // private GoogleGmailApiService googleGmailApiService;

    @Override
    public boolean isOverBudget(BigDecimal expense, LocalDate date, Customer c) throws Exception {
        Integer idCustomer = c.getCustomerId();
        BigDecimal depLead = depensesLeadService.getTotalAmount(idCustomer, date);
        BigDecimal depTicket = depensesTicketService.getTotalAmount(idCustomer, date);

        BigDecimal depTotal = depLead.add(depTicket).add(expense);

        BigDecimal totalBudget = budgetRepository.getTotalAmount(idCustomer, date);
        if (depTotal.compareTo(totalBudget) == 1) {
            // googleGmailApiService.sendEmail(
            //     null,
            //     c.getEmail(),
            //     "Over budget",
            //     "The expenses are over budget. The total lead expenses ="+depLead.intValue()+" and the total ticket expenses = "+depTicket.intValue()+
            //     " giving us the total of "+depTotal.intValue()+" which is over your budget = "+totalBudget.intValue()+". Please confirm if you want to continue the transaction or not by loggin into your account."
            // );
            return true;
        }
        return false;
    }

    @Override
    public boolean isLimitReached(BigDecimal expense, LocalDate date, Customer c) throws Exception  {
        Integer idCustomer = c.getCustomerId();
        BigDecimal depLead = depensesLeadService.getTotalAmount(idCustomer, date);
        BigDecimal depTicket = depensesTicketService.getTotalAmount(idCustomer, date);
        System.out.println("depLead "+depLead.intValue());
        System.out.println("depTicket "+depTicket.intValue());
 
        BigDecimal depTotal = depLead.add(depTicket).add(expense);
        System.out.println("depTotal "+depTotal.intValue());

        BigDecimal limit = getLimit(idCustomer, date);
        if (depTotal.compareTo(limit) == 1 && limit.intValue()!=0) {
            // googleGmailApiService.sendEmail(
            //     null,
            //     c.getEmail(),
            //     "Warning, limit reached",
            //     "This is a warning about the expenses reaching the limit."
            // );
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getLimit(Integer idCustomer, LocalDate date) {
        TauxAlerte tauxAlerte = tauxAlerteService.getMostRecentTaux();
        if (tauxAlerte == null) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal totalBudget = budgetRepository.getTotalAmount(idCustomer, date);
        if (totalBudget == null) {
            return BigDecimal.ZERO;
        }

        return totalBudget.multiply(tauxAlerte.getAmount()).divide(BigDecimal.valueOf(100));
    }

    @Override
    public Budget save(Budget budget) throws Exception {
        if (budget.getAmount().intValue() < 0) throw new Exception("Amount cannot be under 0.");
        return budgetRepository.save(budget);
    }
    
    @Override
    public List<Budget> findByCustomerId(Integer customerId) {
        return budgetRepository.findByCustomerCustomerId(customerId);
    }

}
