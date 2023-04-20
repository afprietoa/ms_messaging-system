package com.makaia.express;

import com.makaia.express.modules.Customer;
import com.makaia.express.modules.Shipment;
import com.makaia.express.modules.common.State;
import com.makaia.express.repositories.CustomerRepository;
import com.makaia.express.repositories.EmployeeRepository;
import com.makaia.express.repositories.PacketRepository;
import com.makaia.express.repositories.ShipmentRepository;
import com.makaia.express.services.CustomerService;
import com.makaia.express.services.EmployeeService;
import com.makaia.express.services.PacketService;
import com.makaia.express.services.ShipmentService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShipmentServiceTest {

    CustomerRepository customerRepository;
    CustomerService customerService;

    PacketRepository packetRepository;
    PacketService packetService;

    EmployeeRepository employeeRepository;
    EmployeeService employeeService;

    ShipmentRepository shipmentRepository;
    ShipmentService shipmentService;

    @Before
    public void setUp(){
        this.customerRepository = mock(CustomerRepository.class);
        this.packetRepository = mock(PacketRepository.class);
        this.employeeRepository = mock(EmployeeRepository.class);
        this.shipmentRepository = mock(ShipmentRepository.class);
        this.shipmentService = new ShipmentService(shipmentRepository, customerRepository, packetRepository,employeeRepository);
    }

    @Test
    public void validateShipment(){
        Customer customer = new Customer(1, "Pepito", "Perez", 3208665231l, "peto@example.com", "Calle falsa 123", "Medellin");
        Shipment shipment = new Shipment(1, "Medellin", "Bogota", "calle 46 # 69-90", "Juan Manuel", 3216884514l, 2, String.valueOf(State.DELIVERED), 100.0, customer);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.findAll()).thenReturn(customerList);

        Shipment packExpress  = shipmentService.create(shipment, customer.getIdCardNumber());

        assertTrue((packExpress.getGuideNumber()!=null && packExpress.getOriginCity()!=null && packExpress.getDestinyCity()!=null
                && packExpress.getDestinyAddress()!=null && packExpress.getRecipientName()!=null &&
                packExpress.getRecipientContact() instanceof Long && packExpress.getDeliveryHour() instanceof Integer &&
                packExpress.getShipmentState()!=null && packExpress.getShippingCosts() instanceof Double));
    }

}
