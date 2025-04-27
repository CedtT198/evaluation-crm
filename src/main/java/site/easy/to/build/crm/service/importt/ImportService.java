package site.easy.to.build.crm.service.importt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.DepensesLead;
import site.easy.to.build.crm.entity.DepensesTicket;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.DTO.*;
import site.easy.to.build.crm.import_csv.CSVFile;
import site.easy.to.build.crm.import_csv.ConstraintCSV;
import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.repository.CustomerRepository;
import site.easy.to.build.crm.repository.LeadRepository;
import site.easy.to.build.crm.repository.TicketRepository;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depenses.DepensesLeadService;
import site.easy.to.build.crm.service.depenses.DepensesTicketService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

@Service
public class ImportService {

  private final CustomerService customerService;
  private final BudgetService budgetService;
  private final DepensesTicketService depensesTicketService;
  private final DepensesLeadService depensesLeadService;
  private final CustomerRepository customerRepository;
  private final LeadRepository leadRepository;
  private final TicketRepository ticketRepository;
  private final TicketService ticketService;
  private final LeadService leadService;
  
@Autowired
  public ImportService(CustomerService customerService, BudgetService budgetService,
      DepensesTicketService depensesTicketService, DepensesLeadService depensesLeadService,
      CustomerRepository customerRepository, LeadRepository leadRepository, TicketRepository ticketRepository,
      TicketService ticketService, LeadService leadService) {
    this.customerService = customerService;
    this.budgetService = budgetService;
    this.depensesTicketService = depensesTicketService;
    this.depensesLeadService = depensesLeadService;
    this.customerRepository = customerRepository;
    this.leadRepository = leadRepository;
    this.ticketRepository = ticketRepository;
    this.ticketService = ticketService;
    this.leadService = leadService;
  }


  public void importCSV(MultipartFile file1, MultipartFile file2, MultipartFile file3) throws Exception {
    // contrainte
    System.out.println("Application contraintes");
    System.out.println("=============================\n");

    CSVFile<CustomerDTO> csvFile2 = new CSVFile<>(file2, ",");
    System.out.println("File : "+file2.getName());
    csvFile2.readAndTransform(v->
      new CustomerDTO((String)v.get("customer_email"), (String)v.get("customer_name"))
    );

    for (CustomerDTO c :csvFile2.getData()) {
      System.out.println("- "+c.getEmail() + " ====> " + c.getName());
    }
    System.out.println("\n");
    


    CSVFile<CustomerBudgetDTO> csvFile3 = new CSVFile<>(file3, ",");
    System.out.println("File : "+file3.getName());
    csvFile3.addConstraint("Budget", ConstraintCSV.BIG_DECIMAL_POSITIVE);
    csvFile3.readAndTransform(v->
      new CustomerBudgetDTO((String)v.get("customer_email"), (BigDecimal)v.get("Budget"))
    );
    
    for (CustomerBudgetDTO c :csvFile3.getData()) {
      System.out.println("- "+c.getEmail() + " ====> " + c.getBudget());
    }
    System.out.println("\n");
 


    CSVFile<DepensesDTO> csvFile1 = new CSVFile<>(file1, ",");
    System.out.println("File : "+file1.getName());
    csvFile1.addConstraint("expense",ConstraintCSV.BIG_DECIMAL_POSITIVE)
          .addConstraint("status", ConstraintCSV.STATUS);
    csvFile1.readAndTransform(v->
      new DepensesDTO(
        (String)v.get("customer_email"),
        (String)v.get("subject_or_name"),
        (String)v.get("type"),
        (String)v.get("status"),
        (BigDecimal)v.get("expense")
      )
    );
    
    for (DepensesDTO c :csvFile1.getData()) {
      System.out.println("- "+c.getEmail() + " ====> " + c.getExpense());
      System.out.println("Type "+c.getType());
      System.out.println("Status "+c.getStatus());
      System.out.println("Subject "+c.getSubject());
      // for (Double d :c.getExpense()) {
      //   System.out.println(d);
      // }
    }
    System.out.println("\n");

    if (csvFile1.getErrors().size()!= 0) {
      System.out.println("Error :\n");
      for (String s : csvFile1.getErrors()) {
        System.out.println("- "+s);
      }
      throw new Exception("erreur d'importation");
    }
    if (csvFile2.getErrors().size()!= 0) {
      System.out.println("Error :\n");
      for (String s : csvFile1.getErrors()) {
        System.out.println("- "+s);
      }
      throw new Exception("erreur d'importation");
    }
    if (csvFile3.getErrors().size()!= 0) {
      System.out.println("Error :\n");
      for (String s : csvFile1.getErrors()) {
        System.out.println("- "+s);
      }
      throw new Exception("erreur d'importation");
    }

    // insertion
    System.out.println("Manomboka ny insertion");
    System.out.println("=============================\n");

    Random random = new Random();
    String[] countries = {"Madagascar", "France", "USA", "Canada"};
    String country = countries[random.nextInt(countries.length)];
    
    String[] states = {"State 1", "State 2", "State 3", "State 4"};
    String state = states[random.nextInt(states.length)];
    
    String[] cities = {"State 1", "State 2", "State 3", "State 4"};
    String city = cities[random.nextInt(cities.length)];

    for (CustomerDTO c :csvFile2.getData()) {
      Customer cust = new Customer(c.getName(), c.getEmail(), "Position "+c.getName(), null, "Adresse de "+c.getName(), city, state, country, "Description de "+c.getName(), null, null, null, null, null, null);
      customerService.save(cust);
    }

    LocalDate startDate = LocalDate.of(2024, 1, 1);
    LocalDate endDate = LocalDate.of(2025, 1, 1);
    long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
    long randomDays = ThreadLocalRandom.current().nextLong(daysBetween+1);
    LocalDate randomDate = startDate.plusDays(randomDays);
    for (CustomerBudgetDTO c :csvFile3.getData()) {
      Customer cust = customerRepository.findByEmail(c.getEmail());
      Budget b = new Budget(null, c.getBudget(), cust, randomDate);
      budgetService.save(b);
    }


    for (DepensesDTO c :csvFile1.getData()) {
      Customer cust = customerRepository.findByEmail(c.getEmail());
      System.out.println(c.getType());
      System.out.println("Double "+c.getExpense());
      System.out.println("Big decimal "+c.getExpense());
      if (c.getType().equalsIgnoreCase("lead")) {
        System.out.println("Tafiditra anaty lead");
        Lead lead = new Lead(c.getSubject(), c.getStatus(), null, null, null,
        null, null, null, null, null, null, cust, null);
        Lead l = leadService.save(lead);

        // for (Double expense : c.getExpense()) {
          DepensesLead dpl = new DepensesLead(null, c.getExpense(), randomDate, l, null, null, true);
          // dpl.setAmount(BigDecimal.valueOf(c.getExpense()));
          depensesLeadService.save(dpl);
        // }
        System.out.println("Vita");
      }
      else if (c.getType().equalsIgnoreCase("ticket")) {
        String[] priorities = {"low", "medium", "high", "closed", "urgent", "critical"};
        String priority = priorities[random.nextInt(priorities.length)];
        System.out.println("Tafiditra anaty ticket");
        Ticket ticket = new Ticket(c.getSubject(), null, c.getStatus(), priority, null, null, cust, null);
        Ticket t = ticketService.save(ticket);
        
        // for (Double expense : c.getExpense()) {
          DepensesTicket dpt = new DepensesTicket(null, c.getExpense(), randomDate, t, null, null, true);
          // dpt.setAmount(BigDecimal.valueOf(c.getExpense()));
          depensesTicketService.save(dpt);
        // }
        System.out.println("Vita");
      }
    }
  } 
}