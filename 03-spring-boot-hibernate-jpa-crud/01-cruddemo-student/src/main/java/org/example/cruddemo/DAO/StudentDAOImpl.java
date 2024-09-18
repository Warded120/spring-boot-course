package org.example.cruddemo.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.cruddemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll(String theData) {
        //refactor the parameter
        theData = "%" + theData + "%";

        //create a query
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.email like :theData order by firstName desc", Student.class);

        //set parameters
        query.setParameter("theData", theData);

        //return the result
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //refactor the parameter
        theLastName = "%" + theLastName + "%";

        //create a query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s where s.lastName like :theLastName order by firstName desc", Student.class);

        //set parameters
        query.setParameter("theLastName", theLastName);

        //return the result
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student theStudent = entityManager.find(Student.class, id);

        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int truncate() {
        return entityManager.createQuery("delete from Student").executeUpdate();
    }
}
