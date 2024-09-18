package com.ivan.springboot.cruddemo.dao;

import com.ivan.springboot.cruddemo.entity.Course;
import com.ivan.springboot.cruddemo.entity.Instructor;
import com.ivan.springboot.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    void save(Course theCourse);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
    void update(Instructor theInstructor);
    void update(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
}
