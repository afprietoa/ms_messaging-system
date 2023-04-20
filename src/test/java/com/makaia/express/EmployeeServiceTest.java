package com.makaia.express;


import com.makaia.express.modules.Employee;
import com.makaia.express.modules.common.Role;
import com.makaia.express.repositories.EmployeeRepository;
import com.makaia.express.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    EmployeeRepository employeeRepository;
    EmployeeService employeeService;

    @Before
    public void setUp(){
        this.employeeRepository = mock(EmployeeRepository.class);
        this.employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void validateEmployee(){
        Employee employee = new Employee(1, "Pepito", "Perez", 3208665231l, "peto@example.com", "Calle falsa 123", "Medellin", 1, "O+", String.valueOf(Role.DELIVERER));
        Employee worker = employeeService.create(employee);

        assertTrue((worker.getIdCardNumber() instanceof Integer && worker.getFirstName()!=null &&
                worker.getLastName()!=null && worker.getCellphone() instanceof  Long &&
                worker.getEmail()!=null && worker.getAddress()!=null &&
                worker.getCity()!=null && worker.getAntiquity() instanceof Integer &&
                worker.getBloodType()!=null && worker.getEmployeeType()!=null));
    }
}
