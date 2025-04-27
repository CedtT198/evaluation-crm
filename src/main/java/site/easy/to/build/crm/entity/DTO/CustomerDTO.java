package site.easy.to.build.crm.entity.DTO;

public class CustomerDTO {
    String email;
    String name;

    
    public CustomerDTO() {}
    public CustomerDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
}
