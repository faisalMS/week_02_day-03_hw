package com.example.ex1_03.modle;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Payload;
import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Park {

    @NotNull(message = "rideID is required")
    @Min(value = 2, message = "You must be length more than 2")
    private String rideID;
    @NotNull(message = "rideName is required")
    @Min(value = 4, message = "You must be length more than 4")
    private String rideName;
    @NotNull(message = "rideType is required")
    @Pattern(regexp ="rollercoaster|thriller|water", message = "Only you can enter these values")
    private String rideType;
    @NotNull(message = "tickets is required")
    @Positive(message = "must be a number")
    private Integer tickets;
    @NotNull(message = "price is required")
    @Positive(message = "must be a number")
    private Integer price;
}
