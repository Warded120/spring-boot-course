package org.example.springbootrestcontroller.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentErrorResponse {
    private int status;
    private String message;
    private Long timestamp;
}
