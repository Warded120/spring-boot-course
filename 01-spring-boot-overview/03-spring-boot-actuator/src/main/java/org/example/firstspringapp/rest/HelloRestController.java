package org.example.firstspringapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/")
    public String hello() {
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
