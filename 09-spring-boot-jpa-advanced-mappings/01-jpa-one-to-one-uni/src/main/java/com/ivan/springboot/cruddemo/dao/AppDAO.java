package com.ivan.springboot.cruddemo.dao;

import com.ivan.springboot.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findById(int theId);
    void deleteById(int theId);
}
