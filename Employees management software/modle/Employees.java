package com.example.ex2_03.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employees {

    @NotNull(message = "Cannot be null")
    @Size(min = 2, message = "You must be length more than 2")
    private String id;
    @NotNull(message = "Cannot be null")
    @Size(min = 4, message = "You must be length more than 4")
    private String name;
    @NotNull(message = "Cannot be null")
    @Min(value = 25, message = "It must be more than 25")
    private Integer age;
    @AssertFalse(message = "must be false")
    private Boolean onLeave;
    @NotNull(message = "Cannot be null")
    @Pattern(regexp = "year", message = "It must be a year")
    private Integer employment;
    @NotNull(message = "Cannot be null")
    private Integer annualLeave;

}
