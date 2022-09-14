package com.example.coollibrary.service;


import com.example.coollibrary.model.Employee;
import com.example.coollibrary.model.Role;
import com.example.coollibrary.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        if(employeeRepository.findById(id).isPresent()) {
               return employeeRepository.findById(id).get();
        }
        return null;
    }
    @Override
    public List<Employee> getEmployeesByRole(Role role) {

        return employeeRepository.findEmployeesByRole(role);
    }
    @Override
    public Employee saveOrUpdateEmployee(Employee employee) {

       return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
         employeeRepository.deleteById(id);
    }


}
