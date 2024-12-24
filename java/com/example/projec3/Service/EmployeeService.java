package com.example.projec3.Service;

import com.example.projec3.ApiResponse.ApiException;
import com.example.projec3.DTO.EmployeeDTOin;
import com.example.projec3.Model.Employee;
import com.example.projec3.Model.MyUser;
import com.example.projec3.Repository.AuthRepsitory;
import com.example.projec3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final AuthRepsitory authRepsitory;
   private final EmployeeRepository employeeRepository;


    public  void registerEmployee(EmployeeDTOin employeeDTOin){
              MyUser myUser=authRepsitory.findMyUserByUsername(employeeDTOin.getUsername());
           if (myUser != null) {
                           throw new ApiException("User already exists");
                      }

                MyUser myUser1=new MyUser();

                       myUser1.setUsername(employeeDTOin.getUsername());
                       myUser1.setPassword(new BCryptPasswordEncoder().encode(employeeDTOin.getPassword()));
                      myUser1.setName(employeeDTOin.getName());
                      myUser1.setRole("Employee");


        Employee employee=new Employee();
        employee.setPosition(employee.getPosition());
        employee.setSalary(employee.getSalary());

        employee.setUser(myUser1);

        myUser1.setEmployee(employee);

         authRepsitory.save(myUser1);
         employeeRepository.save(employee);}


}


