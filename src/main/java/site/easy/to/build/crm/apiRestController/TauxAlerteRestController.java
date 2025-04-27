package site.easy.to.build.crm.apiRestController;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.TauxAlerte;
import site.easy.to.build.crm.service.taux.TauxAlerteService;

@RestController
@RequestMapping("/api/taux-alerte")
public class TauxAlerteRestController {

    private final TauxAlerteService tauxAlerteService;
    
    @Autowired
    public TauxAlerteRestController(TauxAlerteService tauxAlerteService) {
        this.tauxAlerteService = tauxAlerteService;
    }

    @PostMapping("/save")
    public String save(@RequestBody TauxAlerte taux) {
        taux.setId(null);
        System.out.println("Connected to api taux alerte"); 
        try {
            // System.out.println(taux.getId());
            // System.out.println(taux.getAmount());
            // System.out.println(taux.getDateTaux());
            tauxAlerteService.save(taux);
            // System.out.println("Insertion done successfuly.");
            return "Insertion done successfuly.";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
