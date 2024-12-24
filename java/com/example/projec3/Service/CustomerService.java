package com.example.projec3.Service;

import com.example.projec3.ApiResponse.ApiException;
import com.example.projec3.DTO.CustomerDTOin;
import com.example.projec3.Model.Customer;
import com.example.projec3.Model.MyUser;
import com.example.projec3.Repository.AuthRepsitory;
import com.example.projec3.Repository.CustomerRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final AuthRepsitory authRepsitory;
  private final CustomerRepostiory customerRepostiory;



  public  void registerCustomer(CustomerDTOin customerDTOin){
      MyUser myUser=authRepsitory.findMyUserByUsername(customerDTOin.getUsername());
      if (myUser != null) {
                      throw new ApiException("User already exists");
                  }

           MyUser myUser1=new MyUser();

               myUser1.setUsername(customerDTOin.getUsername());
              myUser1.setPassword(new BCryptPasswordEncoder().encode(customerDTOin.getPassword()));
              myUser1.setName(customerDTOin.getName());
               myUser1.setRole("CUSTOMER");

            Customer customer = new Customer();
              customer.setPhoneNumber(customerDTOin.getPhoneNumber());
               customer.setUser(myUser1);


             myUser1.setCustomer(customer);
              authRepsitory.save(myUser1);



  }




















}
