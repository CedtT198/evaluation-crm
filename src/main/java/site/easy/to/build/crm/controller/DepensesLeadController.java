package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.lead.LeadService;

@Controller
@RequestMapping("/depenses/lead") 
public class DepensesLeadController {

    private final DepensesLeadService depensesLeadService;
    private final BudgetService budgetService;
    private final LeadService leadService;
    
    @Autowired
    public DepensesLeadController(DepensesLeadService depensesLeadService, BudgetService budgetService, LeadService leadService) {
        this.depensesLeadService = depensesLeadService;
        this.budgetService = budgetService;
        this.leadService = leadService;
    }


    @PostMapping("/save")
    public String getAllCustomers(@ModelAttribute DepensesLead depensesLead, RedirectAttributes redirectAttributes, Model model){
        depensesLead.setConfirm(true);
        try {
            Lead lead = leadService.findByLeadId(depensesLead.getLead().getLeadId());
            if (budgetService.isLimitReached(depensesLead.getAmount(), depensesLead.getDateDepense(), lead.getCustomer())) {
                // depensesLeadService.save(depensesLead);
                redirectAttributes.addFlashAttribute("success", "Insertion done successfuly with warning. Budget limit has been reached.");
                return "redirect:/employee/lead/expenses";
            }
            if (budgetService.isOverBudget(depensesLead.getAmount(), depensesLead.getDateDepense(), lead.getCustomer())) {
                depensesLead.setConfirm(false);
                // depensesLeadService.save(depensesLead);
                // model.addAttribute("depenses", depensesLead);
                redirectAttributes.addFlashAttribute("warning", "Over budget. Insertion pending waiting for customer confirmation to validate it.");
                // return "redirect:/customer/expenses";
            }
            else {
                redirectAttributes.addFlashAttribute("success", "Insertion done successfuly.");
            }
            depensesLeadService.save(depensesLead);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
        } 
        return "redirect:/employee/lead/expenses";
    }
}
