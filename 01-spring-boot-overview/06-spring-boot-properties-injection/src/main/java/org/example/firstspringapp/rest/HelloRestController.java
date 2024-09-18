package org.example.firstspringapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    //inject properties
    @Value("${gym.homie}")
    private String gymHomie;

    @Value("${gym.king}")
    private String gymKing;

    @GetMapping("/gym")
    public String getGymInfo() {
        return "Gym homie: " + gymHomie + "\nGym KING: " + gymKing;
    }

    @GetMapping("/")
    public String hello(String parameter) {
        return """
                <ul>
                    <li>Hello</li>
                    <li>Spring</li>
                </ul>
                """;
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye";
    }


}
