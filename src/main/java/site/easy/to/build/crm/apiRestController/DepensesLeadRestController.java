package site.easy.to.build.crm.apiRestController;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.lead.LeadService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/depenses-lead")
public class DepensesLeadRestController {

    private final DepensesLeadService depensesLeadService;
    private final LeadService leadService;
    
    @Autowired
    public DepensesLeadRestController(DepensesLeadService depensesLeadService, LeadService leadService) {
        this.depensesLeadService = depensesLeadService;
        this.leadService = leadService;
    }
    
    @GetMapping("/all")
    public List<DepensesLead> findAllDepensesLead() {
        System.out.println("Get all lead depenses");
        return depensesLeadService.findAllOrderByDate();
    }

    @PostMapping("/update")
    public DepensesLead update(@RequestBody DepensesLead dep) {
        DepensesLead d = depensesLeadService.findById(dep.getId());
        d.setAmount(dep.getAmount());
        return depensesLeadService.update(d);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        // System.out.println("id "+id);
        Lead lead = leadService.findByLeadId(id);
        leadService.delete(lead);
        return "Expenses deleted successfuly.";
    }
    
    // @GetMapping("/all-filtered-date")
    // public List<DepensesLead> getFilteredByDate(@PathVariable LocalDate start, @PathVariable LocalDate end) {
    //     return depensesLeadService.findAllOrderAndFilteredByDate(start, end);
    // }
}
