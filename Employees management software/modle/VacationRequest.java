package com.example.ex2_03.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class VacationRequest {

    private String id;
    private String apply;
    private Boolean annualLeave;
    private Boolean onLeave;
}
