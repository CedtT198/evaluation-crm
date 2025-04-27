package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import site.easy.to.build.crm.service.ticket.TicketService;

@Controller
@RequestMapping("/depenses/ticket") 
public class DepensesTicketController {

    private final DepensesTicketService depensesTicketService;
    private final BudgetService budgetService;
    private final TicketService ticketService;
    
    @Autowired
    public DepensesTicketController(DepensesTicketService depensesTicketService, BudgetService budgetService,
            TicketService ticketService) {
        this.depensesTicketService = depensesTicketService;
        this.budgetService = budgetService;
        this.ticketService = ticketService;
    }


    @PostMapping("/save")
    public String getAllCustomers(@ModelAttribute DepensesTicket depensesTicket, RedirectAttributes redirectAttributes, Model model){
        depensesTicket.setConfirm(true); 
        try {
            Ticket ticket = ticketService.findByTicketId(depensesTicket.getTicket().getTicketId());
            if (budgetService.isLimitReached(depensesTicket.getAmount(), depensesTicket.getDateDepense(), ticket.getCustomer())) {
                // depensesTicketService.save(depensesTicket);
                redirectAttributes.addFlashAttribute("success", "Insertion done successfuly with warning. Budget limit has been reached.");
                // return "redirect:/employee/ticket/expenses";
            }
            if (budgetService.isOverBudget(depensesTicket.getAmount(), depensesTicket.getDateDepense(), ticket.getCustomer())) {
                depensesTicket.setConfirm(false);
                // depensesTicketService.save(depensesTicket);
                // model.addAttribute("depenses", depensesTicket);
                redirectAttributes.addFlashAttribute("warning", "Over budget. Insertion pending waiting for customer confirmation to validate it.");
                // return "redirect:/customer/expenses"; 
            }
            else {
                redirectAttributes.addFlashAttribute("success", "Insertion done successfuly.");
            }
            depensesTicketService.save(depensesTicket);
        } catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } 
        return "redirect:/employee/ticket/expenses"; 
    }
}
