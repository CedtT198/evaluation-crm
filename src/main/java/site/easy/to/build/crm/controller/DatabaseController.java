package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import site.easy.to.build.crm.service.database.DatabaseServiceImpl;

@Controller
@RequestMapping("/database")
public class DatabaseController {
    private final DatabaseServiceImpl database;

    @Autowired
    public DatabaseController(DatabaseServiceImpl database) {
        this.database = database;
    }

    @GetMapping("/truncate")  
    public String truncate(RedirectAttributes redirectAttributes) {
        try {
            database.truncate();
            redirectAttributes.addFlashAttribute("success", "All data in database has been cleared successfuly.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/database/reset";
    }
    
    
    @GetMapping("/reset")
    public String reset() {    
        return "database/reset";
    }
    

    @PostMapping("/generate-random-data")
    public String generate(@RequestParam("nbLine") int nbLine, RedirectAttributes redirectAttributes) {
        try {
            database.generateData(nbLine);  
            redirectAttributes.addFlashAttribute("success", nbLine+" random data has been generated successfuly for all tables in database.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/database/generate";
    }

    
    @GetMapping("/generate")
    public String goToGenreationData() {
        return "database/generate";
    }
}
