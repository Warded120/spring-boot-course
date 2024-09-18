package com.ivan.springboot.cruddemo.dao;

import com.ivan.springboot.cruddemo.entity.Course;
import com.ivan.springboot.cruddemo.entity.Instructor;
import com.ivan.springboot.cruddemo.entity.InstructorDetail;
import com.ivan.springboot.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.TransactionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructorToDelete = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> courses = instructorToDelete.getCourses();

        // remove the associations with the courses
        for (Course course : courses) {
            course.setInstructor(null);
        }

        entityManager.remove(instructorToDelete);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove InstructorDetail reference in the instructor object
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id=:id", Course.class);

        query.setParameter("id", theId);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                                            "select i from Instructor i " +
                                               "JOIN FETCH i.courses " +
                                               "JOIN FETCH i.instructorDetail " +
                                               "where i.id=:id", Instructor.class);

        query.setParameter("id", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course courseToDelete = entityManager.find(Course.class, theId);
        entityManager.remove(courseToDelete);
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create the query
        TypedQuery<Course> query = entityManager.createQuery("""
          select c from Course c
            join fetch c.reviews
              where c.id = :id
          """, Course.class);

        query.setParameter("id", theId);

        // execute the query
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create the query
        TypedQuery<Course> query = entityManager.createQuery("""
          select c from Course c
            join fetch c.students
              where c.id = :id
          """, Course.class);

        query.setParameter("id", theId);

        // execute the query
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create the query
        TypedQuery<Student> query = entityManager.createQuery("""
          select s from Student s
            join fetch s.courses
              where s.id = :id
          """, Student.class);

        query.setParameter("id", theId);

        // execute the query
        return query.getSingleResult();    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student studentToDelete = entityManager.find(Student.class, theId);
        entityManager.remove(studentToDelete);
    }
}