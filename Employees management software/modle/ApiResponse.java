package com.example.ex2_03.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor @Data
public class ApiResponse {


    private String message;
    private Integer status;
    private FieldError fieldError;
}
