package com.makaia.express;

import com.makaia.express.modules.Customer;
import com.makaia.express.repositories.CustomerRepository;
import com.makaia.express.services.CustomerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomServiceTest {
    CustomerRepository customerRepository;
    CustomerService customerService;

    @Before
    public void setUp(){
        this.customerRepository = mock(CustomerRepository.class);
        this.customerService = new CustomerService(customerRepository);
    }

    @Test
    public void validateCustomer(){
        Customer customer = new Customer(1, "Pepito", "Perez", 3208665231l, "peto@example.com", "Calle falsa 123", "Medellin");
        Customer client = customerService.create(customer);

        assertTrue((client.getIdCardNumber() instanceof Integer && client.getFirstName()!=null &&
                    client.getLastName()!=null && client.getCellphone() instanceof  Long &&
                    client.getEmail()!=null && client.getAddress()!=null &&
                    client.getCity()!=null));
    }


}
