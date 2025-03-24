package site.easy.to.build.crm.apiRestController;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import java.util.List;

@RestController
@RequestMapping("/api/depenses-lead")
public class DepensesLeadRestController {

    private final DepensesLeadService depensesLeadService;
    
    @Autowired
    public DepensesLeadRestController(DepensesLeadService depensesLeadService) {
        this.depensesLeadService = depensesLeadService;
    }
    
    @GetMapping("/all")
    public List<DepensesLead> findAllDepensesLead() {
        System.out.println("Get all lead depenses");
        return depensesLeadService.findAllOrderByDate();
    }

    @PostMapping("/update")
    public DepensesLead update(@RequestBody DepensesLead dep) {
        return depensesLeadService.update(dep);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        depensesLeadService.delete(id);
        return "Expenses deleted successfuly.";
    }
}
