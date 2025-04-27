package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.easy.to.build.crm.service.importt.ImportService;

@Controller
@RequestMapping("/import")
public class ImportController {

    private final ImportService importService;

    @Autowired
    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @GetMapping("/create")
    public String goToImportPage() {
        return "database/import"; 
    }

    @PostMapping("/csv-file")
    public String importCsvFile(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
    @RequestParam("file3") MultipartFile file3, RedirectAttributes redirectAttributes) {
        if (file1 == null || file2 == null || file3 == null) {
            redirectAttributes.addFlashAttribute("error", "All fileds must be filed."); 
        }
        
        try {
            importService.importCSV(file1, file2, file3);
            redirectAttributes.addFlashAttribute("success", "Import successfuly done.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/import/create";
    }
    
}
