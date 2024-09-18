package org.ivan.springboot.thymeleafdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favouriteLanguage;
    private List<String> favouriteSystems;
}
