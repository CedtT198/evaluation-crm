package site.easy.to.build.crm.apiRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Depenses;
import site.easy.to.build.crm.service.depenses.DepensesService;
import java.util.List;

@RestController
@RequestMapping("/api/depenses")
public class DepensesRestController {

    private final DepensesService depensesService;
    
    @Autowired
    public DepensesRestController(DepensesService depensesService) {
        this.depensesService = depensesService;
    }

    // @GetMapping("/all-per-customer")
    // public List<Depenses> findAllDepensesPerCustomer() {
    //     return depensesService.findAllGroupByCustomer();
    // }
    
    @GetMapping("/all")
    public List<Depenses> findAllDepenses() {
        System.out.println("Get all depenses");
        return depensesService.findAllDepensesPerCustomer();
    }
}
