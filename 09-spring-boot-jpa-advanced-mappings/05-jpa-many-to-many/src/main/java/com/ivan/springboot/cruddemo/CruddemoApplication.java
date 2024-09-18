package com.ivan.springboot.cruddemo;

import com.ivan.springboot.cruddemo.dao.AppDAO;
import com.ivan.springboot.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

	public CruddemoApplication(DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
		this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting student; " + theId);

		appDAO.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);

		Course course1 = new Course("Rubik's cube - how to speed cube");
		Course course2 = new Course("UE5 - game development");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student" + student);
		System.out.println("associated courses: " + student.getCourses());

		appDAO.update(student);
		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding student: " + theId);
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course; " + theId);
		Course course = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());

	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create the course
		Course course = new Course("how to use @ManyToMany");

		// create the students
		Student student1 = new Student("John", "Doe", "john@luv2code.com");
		Student student2 = new Student("Susan", "Public", "susan@luv2code.com");
		Student student3 = new Student("Ivan", "Hrenevych", "ivan@luv2code.com");

		// add students to the course
		course.addStudent(student1);
		course.addStudent(student2);
		course.addStudent(student3);

		// save everything
		System.out.println("Saving course " + course);
		System.out.println("Associated students " + course.getStudents());

		appDAO.save(course);

		System.out.println("Done!");

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course: " + theId);

		appDAO.deleteCourseById(theId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course: " + theId);

		Course course = appDAO.findCourseAndReviewsByCourseId(theId);

		System.out.println("Found course: " + course);
		System.out.println("course reviews: " + course.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course course = new Course("Pacman - how to score a million points");

		// add reviews
		course.addReview(new Review("Hey a cool course, i love it!"));
		course.addReview(new Review("Cool course - job well done"));
		course.addReview(new Review("Eh... not worth the time"));

		// save the course
		appDAO.save(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Finding the course wit id: " + theId);
		Course course = appDAO.findCourseById(theId);

		System.out.println("Updating course: " + course);
		course.setTitle("Enjoy the simple things");

		System.out.println("Updated course: " + course);
		appDAO.update(course);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=  1;

		System.out.println("Finding the instructor with id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Updating instructor: " + instructor);
		instructor.setLastName("Tester");

		System.out.println("Saving instructor: " + instructor);
		appDAO.update(instructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor with id " + theId);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("instructor = " + instructor);
		System.out.println("courses = " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructorId(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("instructor: " + instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		instructor.setCourses(courses);
		System.out.println("courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Getting instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("Associated courses: " + tempInstructor.getCourses());

		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan@gmail.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("some-susan-channel", "Video games");

		// associate this two objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create courses
		Course tempCourse1 = new Course("guitar ultimate course");
		Course tempCourse2 = new Course("The pinball masterclass");

		// add them to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("Saving instructor");
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		appDAO.deleteInstructorDetailById(4);
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;

		appDAO.deleteInstructorById(id);
		System.out.println("Instructor with id " + id + " deleted.");
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor =
				new Instructor("Mahdu", "Patel", "mahdu@gmail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail("some-some-channel", "guitar");

		// associate this two objects
		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: " + instructor);

		// this will also save the instructorDetail object (cascadeType.ALL)
		appDAO.save(instructor);

	}

	private void retrieveInstructor(AppDAO appDAO) {

		Instructor instructor = appDAO.findInstructorById(2);

		System.out.println("Retrieving instructor: " + instructor);
		System.out.println("Retrieving instructorDetail: " + instructor.getInstructorDetail());
	}

	public void retrieveInstructorDetail(AppDAO appDAO) {
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(1);
		System.out.println("Instructor Detail: " + instructorDetail);
		System.out.println("Associated instrucotr: " + instructorDetail.getInstructor());
	}
}
