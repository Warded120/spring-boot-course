package org.example.cruddemo;

import jdk.swing.interop.SwingInterOpUtils;
import org.example.cruddemo.DAO.StudentDAO;
import org.example.cruddemo.DAO.StudentDAOImpl;
import org.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO, StudentDAOImpl studentDAOImpl) {
        return runner -> {
            //truncateStudents(studentDAO);
            //deleteStudent(studentDAO);
            //updateStudent(studentDAO);
            //queryTheStudentsByLastName(studentDAO);
            //queryForStudents(studentDAO);
            //readStudent(studentDAO);
            createMultipleStudents(studentDAO);
            //createStudent(studentDAO);
        };
    }

    private void truncateStudents(StudentDAO studentDAO) {
        System.out.println("deleting all students");
        int numRowsDeleted = studentDAO.truncate();
        System.out.println("deleted " + numRowsDeleted + " students");
    }

    private void deleteStudent(StudentDAO studentDAO) {
        //retrieve a student based on the id
        int studentId = 4;
        System.out.println("deleting student " + studentId);

        //delete the student
        studentDAO.delete(4);
    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrieve a student based on the id: primary key
        int studentId = 4;
        System.out.println("Updating the student with id: " + studentId);
        Student studentToUpdate = studentDAO.findById(studentId);

        //change the first name to the Scooby
        studentToUpdate.setFirstName("John");

        //update the student
        studentDAO.update(studentToUpdate);

        //display the updated student
        System.out.println("Updated the student: " + studentToUpdate);
    }

    private void queryTheStudentsByLastName(StudentDAO studentDAO) {
        //get a list of students
        var students = studentDAO.findByLastName("d");

        //display the list of students
        for (var student : students) {
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //get a list of students
        var students = studentDAO.findAll("o");

        //display the list of students
        for (var student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        //create a student object
        System.out.println("create a student object");
        Student student = new Student("Daffy", "duck", "daffy@gmail.com");

        //save a student
        System.out.println("save a student");
        studentDAO.save(student);

        //display id of a saved student
        System.out.println("saved student. generated id: " + student.getId());

        //retrieve student based on the id
        System.out.println("read a student object");
        Student readStudent = studentDAO.findById(student.getId());

        //display student
        System.out.println(readStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        //create the student object
        System.out.println("Creating student1...");
        Student student1 = new Student("Ivan", "Hrenevych", "hrenevych.ivan@gmail.com");
        Student student2 = new Student("Oleg", "Malabana", "malabana.oleg@gmail.com");
        Student student3 = new Student("Maria", "Benita", "benita.maria@gmail.com");

        // deal with student1 object
        System.out.println("Saving student1...");
        studentDAO.save(student1);
        System.out.println("Saved student1. Generated id: " + student1.getId());

        // deal with student2 object
        System.out.println("Saving student2...");
        studentDAO.save(student2);
        System.out.println("Saved student2. Generated id: " + student2.getId());

        // deal with student3 object
        System.out.println("Saving student3...");
        studentDAO.save(student3);
        System.out.println("Saved student3. Generated id: " + student3.getId());
    }

    private void createStudent(StudentDAO studentDAO) {
        //create the student object
        System.out.println("Creating student...");
        Student student = new Student("Ivan", "Hrenevych", "hrenevych.ivan@gmail.com");

        //save the student object
        System.out.println("Saving student...");
        studentDAO.save(student);

        //display the id of the saved student
        System.out.println("Saved student. Generated id: " + student.getId());
    }
}
