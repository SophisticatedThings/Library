package com.example.coollibrary.repository;


import com.example.coollibrary.model.Employee;
import com.example.coollibrary.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        public List<Employee> findEmployeesByRole(Role role);
        public Employee findEmployeeByEmail(String email);
}
