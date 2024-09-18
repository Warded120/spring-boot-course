package com.ivan.springboot.mvc.model;

import com.ivan.springboot.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    private String lastName;

    @NotNull(message = "required")
    @Min(value = 0, message = "Must be greater or equal to 0")
    @Max(value = 10, message = "Must be less or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[A-Za-z0-9]{5}", message = "Only 5 symbols/digits")
    private String postalCode;

    @CourseCode(value = "IVAN", message = "must start with IVAN...")
    private String courseCode;
}
