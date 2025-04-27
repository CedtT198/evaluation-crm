package site.easy.to.build.crm.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.service.budget.BudgetService;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;
    
    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }


    @PostMapping("/save")
    public String getAllCustomers(@ModelAttribute Budget budget, RedirectAttributes redirectAttributes){
        try {
            // budget.setDateBudget(LocalDate.now());
            budgetService.save(budget);
            
            redirectAttributes.addFlashAttribute("success", "Insertion done successfuly.");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } 
        return "redirect:/employee/customer/manager/customer-budget";
    }
}
