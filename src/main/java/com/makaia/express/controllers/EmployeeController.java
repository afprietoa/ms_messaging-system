package com.makaia.express.controllers;

import com.makaia.express.modules.Employee;
import com.makaia.express.services.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's employees", notes= "this searches all employees", response = Employee.class)
    @PreAuthorize("hasRole('READ')")
    @GetMapping("/employee/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        return this.employeeService.index();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="employee object", notes= "this searches a employee by id", response = Employee.class)
    @PreAuthorize("hasRole('READ')")
    @GetMapping("/employee/by_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Employee> getEmployeeById(@PathVariable("id") int id){
        return this.employeeService.show(id);
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created employee success")
    })
    @ApiOperation(value="employee", notes= "this create a employee", response = Employee.class)
    @PreAuthorize("hasRole('WRITE')")
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee insertEmployee(@RequestBody Employee employee){
        return this.employeeService.create(employee);
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        return this.employeeService.update(id, employee);
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteEmployee(@PathVariable("id") int id){
        return this.employeeService.delete(id);
    }

}
