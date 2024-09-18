package com.ivan.springboot.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String prefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        prefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if(s != null) {
            result = s.startsWith(prefix) &&  s.length() > prefix.length() ;
        }
        else {
            result = true;
        }

        return result;
    }
}
