package org.example.cruddemo.DAO;

import org.example.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll(String theData);
    List<Student> findByLastName(String theLastName);
    void update(Student theStudent);
    void delete(int id);
    int truncate();
}
