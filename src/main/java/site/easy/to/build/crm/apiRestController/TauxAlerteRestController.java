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

    @GetMapping("/save")
    public String save(@RequestBody TauxAlerte taux) {
        System.out.println("Connectec to api taux alerte"); 
        try {
            tauxAlerteService.save(taux);
            return "Insertion done successfuly.";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
