package com.makaia.express.services;

import com.makaia.express.modules.Customer;
import com.makaia.express.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     *
     * @return
     */
    public List<Customer> index(){
        List<Customer> resultList = (List<Customer>) this.customerRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any customer in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Customer> show(int id){
        Optional<Customer> result = this.customerRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested customer.id does not exist.");
        return  result;
    }

    /**
     *
     * @param newCustomer
     * @return
     */
    public Customer create(Customer newCustomer){
        if(newCustomer.getIdCardNumber() != null){
            Optional<Customer> tempCustomer = this.customerRepository.findById(newCustomer.getIdCardNumber());
            if(tempCustomer.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newCustomer.getEmail() != null) && (newCustomer.getFirstName() != null) &&
                (newCustomer.getLastName() != null) && (newCustomer.getCellphone() != null)){
            this.customerRepository.save(newCustomer);
            return newCustomer;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param customer
     * @return
     */
    public  Customer update(int id, Customer customer){
        if(id > 0){
            Optional<Customer> tempCustomer = this.customerRepository.findById(id);
            if(tempCustomer.isPresent()){
                if(customer.getFirstName() != null)
                    tempCustomer.get().setFirstName(customer.getFirstName());
                if (customer.getLastName() != null)
                    tempCustomer.get().setLastName(customer.getLastName());
                if(customer.getCellphone() != null)
                    tempCustomer.get().setCellphone(customer.getCellphone());
                if(customer.getEmail() != null)
                    tempCustomer.get().setEmail(customer.getEmail());
                if(customer.getAddress() != null)
                    tempCustomer.get().setAddress(customer.getAddress());
                if(customer.getCity() != null)
                    tempCustomer.get().setCity(customer.getCity());
                this.customerRepository.save(tempCustomer.get());
                return tempCustomer.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Customer.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.customerRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Customer cannot be deleted.");
    }
}
