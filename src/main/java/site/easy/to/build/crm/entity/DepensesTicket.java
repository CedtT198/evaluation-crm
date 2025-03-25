package site.easy.to.build.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "depenses_ticket")
public class DepensesTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
 
    @Column(name = "amount", precision = 10, scale = 0)
    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;

    @Column(name = "date_depense", nullable = false)
    private LocalDate dateDepense;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "summary")
    private String summary;
    
    @Column(name = "confirm")
    private boolean confirm;

    // Constructeurs
    public DepensesTicket() {}
    public DepensesTicket(Integer id, BigDecimal amount, LocalDate dateDepense, Ticket ticket, String description, String summary, boolean confirm) {
        this.id = id;
        this.amount = amount;
        this.dateDepense = dateDepense;
        this.ticket = ticket;
        this.description = description;
        this.summary = summary;
        this.confirm = confirm;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getDateDepense() {
        return dateDepense;
    }
    public void setDateDepense(LocalDate dateDepense) {
        this.dateDepense = dateDepense;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
    public boolean isConfirm() {
        return confirm;
    }
}
