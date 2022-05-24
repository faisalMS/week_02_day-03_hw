package com.example.ex2_03.controller;

import com.example.ex2_03.modle.ApiResponse;
import com.example.ex2_03.modle.Employees;
import com.example.ex2_03.modle.VacationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("Api/V1/employee")
public class EmployeesManagementController {

    private ArrayList<Employees> employeesArrayList = new ArrayList<>();

    @GetMapping
    public ResponseEntity getEmployee() {
        return ResponseEntity.status(200).body(employeesArrayList);
    }


    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employees employees, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, errors.getFieldError());
            return ResponseEntity.status(400).body(new ApiResponse(message, 400, errors.getFieldError()));

        }
        employeesArrayList.add(employees);
        return ResponseEntity.status(200).body("Added");
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody @Valid Employees employees, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, errors.getFieldError());
            return ResponseEntity.status(400).body(new ApiResponse(message, 400, errors.getFieldError()));
        }
        for (int i = 0; i < employeesArrayList.size(); i++) {
            if (employees.getId().equals(employeesArrayList.get(i).getId())) {
                employeesArrayList.set(i, employees);
                return ResponseEntity.status(200).body("Updated");
            }
        }
        return ResponseEntity.status(400).body("No found");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@RequestBody String id) {
        for (int i = 0; i < employeesArrayList.size(); i++) {
            if (id.equals(employeesArrayList.get(i).getId())) {
                employeesArrayList.remove(i);
                return ResponseEntity.status(200).body("Deleted");
            }
        }
        return ResponseEntity.status(400).body("no found");
    }


    @PutMapping("endPont")
    public ResponseEntity endPoint(@RequestBody @Valid VacationRequest vacationRequest) {

        for (int i = 0; i < employeesArrayList.size(); i++) {
            Employees employees = employeesArrayList.get(i);
            if (employees.equals(employees.getId())) {
                if (employees.getAnnualLeave() > 0) {
                    employees.setOnLeave(true);
                    return ResponseEntity.status(200).body("On leave");
                }else{
                    return ResponseEntity.status(200).body("Annual leave");
                }
            }
            return ResponseEntity.status(200).body("Invalid");
        }
        return ResponseEntity.status(400).body("No found");
    }
}
