package com.example.coollibrary.service;

import com.example.coollibrary.model.Employee;
import com.example.coollibrary.model.Role;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmployees();

    public Employee getEmployeeById(Long id);

    public List<Employee> getEmployeesByRole(Role role);

    public Employee saveOrUpdateEmployee(Employee employee );

    public void deleteEmployeeById(Long id);









}
