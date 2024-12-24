package com.example.projec3.Controller;

import com.example.projec3.ApiResponse.ApiResponse;
import com.example.projec3.DTO.CustomerDTOin;
import com.example.projec3.DTO.EmployeeDTOin;
import com.example.projec3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @PostMapping("/registerEmployee")
    public ResponseEntity registerCustomer(@RequestBody @Valid EmployeeDTOin employeeDTOin){
        employeeService.registerEmployee(employeeDTOin);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Hello to our web"));

    }
}
