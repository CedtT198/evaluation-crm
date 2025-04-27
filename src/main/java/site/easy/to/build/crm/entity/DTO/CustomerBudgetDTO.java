package site.easy.to.build.crm.entity.DTO;

import java.math.BigDecimal;

public class CustomerBudgetDTO {
    String email;
    // Double budget;
    BigDecimal budget;

    public CustomerBudgetDTO() {}
    public CustomerBudgetDTO(String email, BigDecimal budget) {
        this.email = email;
        this.budget = budget;
    }
    // public CustomerBudgetDTO(String email, Double budget) {
    //     this.email = email;
    //     this.budget = budget;
    // }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
