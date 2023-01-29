package com.artem.library.controllers;


import com.artem.library.exception_handling.EmployeeHandler.NoEmployeesException;
import com.artem.library.exception_handling.EmployeeHandler.NoSuchEmployeeException;
import com.artem.library.exception_handling.NoRequestBodyException;
import com.artem.library.model.Employee;
import com.artem.library.model.Role;
import com.artem.library.model.Status;
import com.artem.library.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {

        this.employeeService = employeeService;

    }

    @GetMapping
    @PreAuthorize("hasAuthority('employees:read')")
    public List<Employee> getEmployees(@RequestParam(value="role",required = false) Role role) {


        if( employeeService.getEmployees().isEmpty()){
            throw new NoEmployeesException("There is no employees in database");
        }
        if(role != null){
            List<Employee> employees = employeeService.getEmployeesByRole(role);
            if(employees.isEmpty()){
                throw new NoSuchEmployeeException("There is not employees with role = " + role);
            }
            return employeeService.getEmployeesByRole(role);
        }
        return employeeService.getEmployees();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('employees:read')")
    public Employee getEmployeeById(@PathVariable("id") Long id){

        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id = " + id);
        }
        return employee;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('employees:write')")
    public Employee saveOrUpdateEmployee(@RequestBody Employee employee){

         if( employee == null){
             throw new NoRequestBodyException("Please, enter an employee to update/save");
         }
         if(employee.getId() == null)
              employee.setPassword(new BCryptPasswordEncoder(12).encode(employee.getPassword()));
        if(employee.getStatus() == null){
            employee.setStatus(Status.ACTIVE);
        }
         return employeeService.saveOrUpdateEmployee(employee);

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('employees:write')")
    public void deleteEmployeeById(@PathVariable("id") Long id){


         Employee employee = employeeService.getEmployeeById(id);
         if(employee == null){
             throw new NoSuchEmployeeException("there is no employee with id = " + id + " to remove");
         }

         employeeService.deleteEmployeeById(id);

    }

}

