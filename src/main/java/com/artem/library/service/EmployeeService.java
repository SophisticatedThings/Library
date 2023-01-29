package com.artem.library.service;

import com.artem.library.model.Employee;
import com.artem.library.model.Role;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmployees();

    public Employee getEmployeeById(Long id);

    public List<Employee> getEmployeesByRole(Role role);

    public Employee saveOrUpdateEmployee(Employee employee );

    public void deleteEmployeeById(Long id);









}
