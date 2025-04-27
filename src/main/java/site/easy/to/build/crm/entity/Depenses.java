package site.easy.to.build.crm.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "depenses")
public class Depenses {

    @Id
    @Column(name = "id")  
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date_depense")
    private LocalDate dateDepense;

    @Column(name = "source")
    private String source;  // "Lead" ou "Ticket"

    @Column(name = "reference_id")
    private Integer referenceId;  // ID du lead ou du ticket
    
    @Column(name = "customer_id")
    private Integer customerId; 
    
    // @ManyToOne
    // @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    // private Customer customer;

    @Column(name = "description")
    private String description;

    @Column(name = "summary")
    private String summary;

    @Column(name = "confirm")
    private boolean confirm;

    

    public Depenses() {}
    // public Depenses(Integer id, BigDecimal amount, LocalDate dateDepense, String source, Integer referenceId,
    //         Customer customer, String description, String summary, boolean confirm) {
    //     this.id = id;
    //     this.amount = amount;
    //     this.dateDepense = dateDepense;
    //     this.source = source;
    //     this.referenceId = referenceId;
    //     this.customer = customer;
    //     this.description = description;
    //     this.summary = summary;
    //     this.confirm = confirm;
    // }

    public Depenses(Integer id, BigDecimal amount, LocalDate dateDepense, String source, Integer referenceId,
            Integer customerId, String description, String summary, boolean confirm) {
        this.id = id;
        this.amount = amount;
        this.dateDepense = dateDepense;
        this.source = source;
        this.referenceId = referenceId;
        this.customerId = customerId;
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
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
    public boolean isConfirm() {
        return confirm;
    }
    public Integer getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    // public Customer getCustomer() {
    //     return customer;
    // }
    // public void setCustomer(Customer customer) {
    //     this.customer = customer;
    // }
}
