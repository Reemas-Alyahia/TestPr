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

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepsitory authRepsitory;
    private final CustomerRepostiory customerRepostiory;

    public List<MyUser> getAllUser(){
        return authRepsitory.findAll();
    }

    public void register(MyUser myUser){
        MyUser myUser1=authRepsitory.findMyUserByUsername(myUser.getUsername());
        if(myUser1!=null){
            throw new ApiException("User Already there");
        }
        myUser.setRole("Admin");
        String hashPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);

        authRepsitory.save(myUser);
    }



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
        customerRepostiory.save(customer);


    }
}
