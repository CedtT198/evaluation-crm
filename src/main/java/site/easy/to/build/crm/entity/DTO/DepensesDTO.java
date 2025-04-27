package site.easy.to.build.crm.entity.DTO;

import java.math.BigDecimal;

public class DepensesDTO {
    String email;
    String subject;
    String type;
    String status;
    // List<Double> expense;
    // Double expense;
    BigDecimal expense;

    public DepensesDTO() {}
    public DepensesDTO(String email, String subject, String type, String status, BigDecimal expense) {
        this.email = email;
        this.subject = subject;
        this.type = type;
        this.status = status;
        this.expense = expense;
    }
    // public DepensesDTO(String email, String subject, String type, String status, List<Double> expense) {
    //     this.email = email;
    //     this.subject = subject;
    //     this.type = type;
    //     this.status = status;
    //     this.expense = expense;
    // }
    // public DepensesDTO(String email, String subject, String type, String status, Double expense) {
    //     this.email = email;
    //     this.subject = subject;
    //     this.type = type;
    //     this.status = status;
    //     this.expense = expense;
    // }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    
    // public Double getExpense() {
    //     return expense;
    // }
    // public void setExpense(Double expense) {
    //     this.expense = expense;
    // }
    // public List<Double> getExpense() {
    //     return expense;
    // }
    // public void setExpense(List<Double> expense) {
    //     this.expense = expense;
    // }
}
