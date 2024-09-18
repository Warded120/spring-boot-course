package org.example.springbootrestcrudemployee.DAO;

import jakarta.persistence.EntityManager;
import org.example.springbootrestcrudemployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    //set up a field for entity manager
    EntityManager entityManager;

    //set a constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        var query = entityManager.createQuery("from Employee", Employee.class);

        //exectute the query and get the result list
        List<Employee> employees = query.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee dbEmployee = entityManager.find(Employee.class, theId);

        //return employee
        return dbEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //insert or update employee based on the id: if(id == 0) { insert(); }
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return updated/saved dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //remove employee
        entityManager.remove(theEmployee);
    }
}
