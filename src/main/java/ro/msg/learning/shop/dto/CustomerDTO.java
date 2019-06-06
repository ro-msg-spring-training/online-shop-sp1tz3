package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;

    public static CustomerDTO ofEntity(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPassword(customer.getPassword());
        dto.setUsername(customer.getUsername());
        dto.setEmailAddress(customer.getEmailAddress());
        return dto;
    }
}
