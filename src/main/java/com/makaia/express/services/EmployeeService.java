package com.makaia.express.services;

import com.makaia.express.modules.Employee;
import com.makaia.express.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     * @return
     */
    public List<Employee> index(){
        List<Employee> resultList = (List<Employee>) this.employeeRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any employee in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Employee> show(int id){
        Optional<Employee> result = this.employeeRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested employee.id does not exist.");
        return  result;
    }

    /**
     *
     * @param newEmployee
     * @return
     */
    public Employee create(Employee newEmployee){
        if(newEmployee.getIdCardNumber() != null){
            Optional<Employee> tempEmployee = this.employeeRepository.findById(newEmployee.getIdCardNumber());
            if(tempEmployee.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newEmployee.getEmail() != null) && (newEmployee.getFirstName() != null) &&
                (newEmployee.getLastName() != null) && (newEmployee.getCellphone() != null)){
            return this.employeeRepository.save(newEmployee);
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param employee
     * @return
     */
    public  Employee update(int id, Employee employee){
        if(id > 0){
            Optional<Employee> tempEmployee = this.employeeRepository.findById(id);
            if(tempEmployee.isPresent()){
                if(employee.getFirstName() != null)
                    tempEmployee.get().setFirstName(employee.getFirstName());
                if (employee.getLastName() != null)
                    tempEmployee.get().setLastName(employee.getLastName());
                if(employee.getCellphone() != null)
                    tempEmployee.get().setCellphone(employee.getCellphone());
                if(employee.getEmail() != null)
                    tempEmployee.get().setEmail(employee.getEmail());
                if(employee.getAddress() != null)
                    tempEmployee.get().setAddress(employee.getAddress());
                if(employee.getCity() != null)
                    tempEmployee.get().setCity(employee.getCity());
                if(employee.getAntiquity() != null)
                    tempEmployee.get().setAntiquity(employee.getAntiquity());
                if(employee.getBloodType() != null)
                    tempEmployee.get().setBloodType(employee.getBloodType());
                if(employee.getEmployeeType() != null)
                    tempEmployee.get().setEmployeeType(employee.getEmployeeType());
                return this.employeeRepository.save(tempEmployee.get());
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Employee.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.employeeRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Employee cannot be deleted.");
    }

}
