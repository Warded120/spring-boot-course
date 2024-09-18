package org.example.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    //init method
    @PostConstruct
    public void postConstruct() {
        System.out.println("Class: " + getClass().getSimpleName() + " created");
    }

    //destroy method
    @PreDestroy
    public void PreDestroy() {
        System.out.println("Class: " + getClass().getSimpleName() + " destroying");
    }
}
