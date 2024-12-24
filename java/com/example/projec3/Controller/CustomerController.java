package com.example.projec3.Controller;

import com.example.projec3.ApiResponse.ApiResponse;
import com.example.projec3.DTO.CustomerDTOin;
import com.example.projec3.DTO.EmployeeDTOin;
import com.example.projec3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/registerCu")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTOin customerDTOin){
        customerService.registerCustomer(customerDTOin);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Hello to our web"));

    }

}
