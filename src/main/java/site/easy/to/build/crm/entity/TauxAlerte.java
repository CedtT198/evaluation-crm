package site.easy.to.build.crm.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "taux_alerte")
public class TauxAlerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "amount", precision = 10, scale = 0)
    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Amount must be greater than or equal to 0.00")
    private BigDecimal amount;

    @Column(name = "date_taux", nullable = false)
    private LocalDate dateTaux;

    // Constructeurs
    public TauxAlerte() {}
    public TauxAlerte(Integer id, BigDecimal amount, LocalDate dateTaux) {
        this.id = id;
        this.amount = amount;
        this.dateTaux = dateTaux;
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
    public LocalDate getDateTaux() {
        return dateTaux;
    }
    public void setDateTaux(LocalDate dateTaux) {
        this.dateTaux = dateTaux;
    }
}
