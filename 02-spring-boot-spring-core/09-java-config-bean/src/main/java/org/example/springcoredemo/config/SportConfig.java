package org.example.springcoredemo.config;

import org.example.springcoredemo.common.Coach;
import org.example.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic") //use this qualifier in a rest controller
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
