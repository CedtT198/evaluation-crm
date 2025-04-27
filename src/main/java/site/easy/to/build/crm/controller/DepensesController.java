package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.easy.to.build.crm.entity.Depenses;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.depenses.DepensesService;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.util.AuthenticationUtils;

@Controller
@RequestMapping("/depenses") 
public class DepensesController {

    private final DepensesService depensesService;
    private final DepensesLeadService depensesLeadService;
    private final DepensesTicketService depensesTicketService;
    private final AuthenticationUtils authenticationUtils;
    
    @Autowired
    public DepensesController(DepensesService depensesService, DepensesLeadService depensesLeadService, DepensesTicketService depensesTicketService,
    AuthenticationUtils authenticationUtils) {
        this.depensesService = depensesService;
        this.depensesLeadService = depensesLeadService;
        this.depensesTicketService = depensesTicketService;
        this.authenticationUtils = authenticationUtils;
    }
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        Depenses depenses = depensesService.findById(id);
        if (depenses.getSource().equals("Lead")) { 
            DepensesLead dl = depensesLeadService.findById(depenses.getId());
            dl.setConfirm(false);
            depensesLeadService.update(dl);
        }
        else {
            DepensesTicket dl = depensesTicketService.findById(depenses.getId());
            dl.setConfirm(false);
            depensesTicketService.update(dl);
        }
        return "redirect:/customer/expenses";
    }
}
