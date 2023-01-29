package com.artem.library.repository;


import com.artem.library.model.Employee;
import com.artem.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        public List<Employee> findEmployeesByRole(Role role);
        public Employee findEmployeeByEmail(String email);
}
