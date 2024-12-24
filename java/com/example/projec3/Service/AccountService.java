package com.example.projec3.Service;

import com.example.projec3.ApiResponse.ApiException;
import com.example.projec3.Model.Account;
import com.example.projec3.Model.Customer;
import com.example.projec3.Model.MyUser;
import com.example.projec3.Repository.AccountRepository;
import com.example.projec3.Repository.AuthRepsitory;
import com.example.projec3.Repository.CustomerRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AuthRepsitory authRepsitory;
    private final AccountRepository accountRepository;
    private final CustomerRepostiory customerRepostiory;

  public void newBnkAccount(Account account, Integer auth ){
      Customer customer=customerRepostiory.findCustomerById(auth);
      if(customer==null){
          throw new ApiException("Usre id not found");
      }
       account.setCustomer(customer);
      accountRepository.save(account);
  }
     //Active a bank account



    /// View account details
  


}
