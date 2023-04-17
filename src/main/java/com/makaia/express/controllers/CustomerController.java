package com.makaia.express.controllers;

import com.makaia.express.modules.Customer;
import com.makaia.express.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value="---", description = "This is communication between customer controller and all associative customer services")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's customers", notes= "this searches all customers", response = Customer.class)
    @GetMapping("/customer/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        return this.customerService.index();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="customer object", notes= "this searches a customer by dni", response = Customer.class)
    @GetMapping("/customer/by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Customer> getCustomerById(@PathVariable("id") int id){
        return this.customerService.show(id);
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created customer success")
    })
    @ApiOperation(value="customer", notes= "this create a customer", response = Customer.class)
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer insertCustomer(@RequestBody Customer customer){
        return this.customerService.create(customer);
    }

    @PutMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
        return this.customerService.update(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteCustomer(@PathVariable("id") int id){
        return this.customerService.delete(id);
    }
}
