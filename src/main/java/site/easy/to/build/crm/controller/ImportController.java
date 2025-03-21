package site.easy.to.build.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/import")
public class ImportController {
    
    @GetMapping("/create")
    public String goToImportPage() {
        return "database/import"; 
    }

    @PostMapping("/csv-file")
    public String importCsvFile(RedirectAttributes redirectAttributes) {
        try {
            // traitement
            redirectAttributes.addFlashAttribute("success", "CSV file loaded successfuly.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/import/create";
    }
    
}
