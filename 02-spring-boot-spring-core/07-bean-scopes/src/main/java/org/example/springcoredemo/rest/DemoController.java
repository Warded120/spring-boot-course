package org.example.springcoredemo.rest;

import org.example.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for the dependency
    private Coach theCoach;
    private Coach theAnotherCoach;

    //you can name the function as you want
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach,
                          @Qualifier("cricketCoach") Coach anotherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.theCoach = myCoach;
        this.theAnotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return theCoach.getDailyWorkout();
    }

    //cricketCoach have SCOPE_PROTOTYPE: false
    //Other coaches have SCOPE_SINGLETON: true
    @GetMapping("/check")
    public String getCheck() {
        return "theCoach == theAnotherCoach: " + (theCoach == theAnotherCoach);
    }
}
