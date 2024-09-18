package org.example.springbootrestcrudemployee.DAO;

import org.example.springbootrestcrudemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
