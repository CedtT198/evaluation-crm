package site.easy.to.build.crm.apiRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import site.easy.to.build.crm.service.ticket.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/depenses-ticket")
public class DepensesTicketRestController {

    private final DepensesTicketService depensesTicketService;
    private final TicketService ticketService;
    
    @Autowired
    public DepensesTicketRestController(DepensesTicketService depensesTicketService, TicketService ticketService) {
        this.depensesTicketService = depensesTicketService;
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<DepensesTicket> findAllDepensesTicket() {
        System.out.println("Get all ticket depenses");
        return depensesTicketService.findAllOrderByDate();
    }

    @PostMapping("/update")
    public DepensesTicket update(@RequestBody DepensesTicket dep) {
        DepensesTicket d = depensesTicketService.findById(dep.getId());
        d.setAmount(dep.getAmount());
        return depensesTicketService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("id "+id);
        Ticket ticket = ticketService.findByTicketId(id);
        ticketService.delete(ticket);
        return "Expenses deleted successfuly.";
    }
}
