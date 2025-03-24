package site.easy.to.build.crm.apiRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import java.util.List;

@RestController
@RequestMapping("/api/depenses-ticket")
public class DepensesTicketRestController {

    private final DepensesTicketService depensesTicketService;
    
    @Autowired
    public DepensesTicketRestController(DepensesTicketService depensesTicketService) {
        this.depensesTicketService = depensesTicketService;
    }

    @GetMapping("/all")
    public List<DepensesTicket> findAllDepensesTicket() {
        System.out.println("Get all ticket depenses");
        return depensesTicketService.findAllOrderByDate();
    }

    @GetMapping("/update")
    public DepensesTicket update(@RequestBody DepensesTicket dep) {
        return depensesTicketService.update(dep);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        depensesTicketService.delete(id);
        return "Expenses deleted successfuly.";
    }
}
