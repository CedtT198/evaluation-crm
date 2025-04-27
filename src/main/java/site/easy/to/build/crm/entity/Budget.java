package site.easy.to.build.crm.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "amount", precision = 10, scale = 0)
    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "date_budget", nullable = false)
    private LocalDate dateBudget;

    // Constructeurs
    public Budget() {}
    public Budget(Integer id, BigDecimal amount, Customer customer, LocalDate dateBudget) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
        this.dateBudget = dateBudget;
    }

    // Getters et Setters
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDateBudget() {
        return dateBudget;
    }

    public void setDateBudget(LocalDate dateBudget) {
        this.dateBudget = dateBudget;
    }
}
