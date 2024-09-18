package com.ivan.springboot.cruddemo.dao;

import com.ivan.springboot.cruddemo.entity.Instructor;
import com.ivan.springboot.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
}
