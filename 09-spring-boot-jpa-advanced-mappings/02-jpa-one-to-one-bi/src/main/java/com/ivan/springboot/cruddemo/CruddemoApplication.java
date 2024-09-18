package com.ivan.springboot.cruddemo;

import com.ivan.springboot.cruddemo.dao.AppDAO;
import com.ivan.springboot.cruddemo.entity.Instructor;
import com.ivan.springboot.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			deleteInstructorDetail(appDAO);
		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		appDAO.deleteInstructorDetailById(4);
	}

	private void removeInstructor(AppDAO appDAO) {
		int id = 2;

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

		Instructor instructor = appDAO.findById(2);

		System.out.println("Retrieving instructor: " + instructor);
		System.out.println("Retrieving instructorDetail: " + instructor.getInstructorDetail());
	}

	public void retrieveInstructorDetail(AppDAO appDAO) {
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(1);
		System.out.println("Instructor Detail: " + instructorDetail);
		System.out.println("Associated instrucotr: " + instructorDetail.getInstructor());
	}
}
