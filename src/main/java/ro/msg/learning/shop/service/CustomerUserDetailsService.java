package ro.msg.learning.shop.service;

/*
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Customer customer = customerRepository.findByUsername(s).orElseThrow(CustomerNotFoundException::new);
        return new User(customer.getUsername(), customer.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
    }
}

 */


