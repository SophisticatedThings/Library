package com.example.coollibrary.controllers;


import com.example.coollibrary.exception_handling.EmployeeHandler.NoEmployeesException;
import com.example.coollibrary.exception_handling.EmployeeHandler.NoSuchEmployeeException;
import com.example.coollibrary.exception_handling.NoRequestBodyException;
import com.example.coollibrary.model.Employee;
import com.example.coollibrary.model.Role;
import com.example.coollibrary.model.Status;
import com.example.coollibrary.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Employee getEmployeeById(@PathVariable("id") Long id){

        Employee employee = employeeService.getEmployeeById(id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id = " + id);
        }
        return employee;
    }
    @PostMapping
    public Employee saveOrUpdateEmployee(@RequestBody Employee employee){

         if( employee == null){
             throw new NoRequestBodyException("Please, enter an employee to update/save");
         }

         if(employee.getStatus() == null){
            employee.setStatus(Status.ACTIVE);
        }
         return employeeService.saveOrUpdateEmployee(employee);

    }
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id){


         Employee employee = employeeService.getEmployeeById(id);
         if(employee == null){
             throw new NoSuchEmployeeException("there is no employee with id = " + id + " to remove");
         }

         employeeService.deleteEmployeeById(id);

    }

}

