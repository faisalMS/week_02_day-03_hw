package com.example.ex1_03.controller;


import com.example.ex1_03.modle.ApiResponse;
import com.example.ex1_03.modle.Park;
import com.example.ex1_03.modle.Tickets;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("Api/V1/Ride")

public class ParkController {

    private ArrayList<Park> parks = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Park>> getParks(){
        return ResponseEntity.status(200).body(parks);
    }


    @PostMapping
    private ResponseEntity addRide(@RequestBody @Valid Park park, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, errors.getFieldError());
            return  ResponseEntity.status(400).body(new ApiResponse(message, 400, errors.getFieldError()));
        }
        parks.add(park);
        return ResponseEntity.status(200).body(parks);
    }


    @PutMapping("/update")
    public ResponseEntity updateRide(@RequestBody @Valid Park park,  Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, errors.getFieldError());
            return  ResponseEntity.status(400).body(new ApiResponse(message, 400, errors.getFieldError()));
        }
        for (int i = 0; i < parks.size(); i++){
            if (park.getRideID().equals(parks.get(i).getRideID())) {
                parks.set(i, park);
                return ResponseEntity.status(200).body("Update");
            }
        }
        return ResponseEntity.status(400).body("No found");
    }


    @DeleteMapping("/delete")
    private ResponseEntity deleteRide(@RequestBody String id){
        for (int i = 0; i < parks.size(); i++){
            if (id.equals(parks.get(i).getRideID())){
                parks.remove(i);
                return ResponseEntity.status(200).body("Deleted");
            }
        }
        return ResponseEntity.status(400).body("No found");
    }


    @PutMapping("endPont")
    public ResponseEntity endPoint(@RequestBody @Valid Tickets tickets) {

        for (int i = 0; i < parks.size(); i++) {

            if (tickets.getId().equals(parks.get(i).getRideID())) {
                parks.get(i).setTickets(parks.get(i).getTickets() - 1);
                return ResponseEntity.status(400).body("Purchased");
            }
        }
        return  ResponseEntity.status(400).body("Invalid");
    }
}
