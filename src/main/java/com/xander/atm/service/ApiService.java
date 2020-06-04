package com.xander.atm.service;

import com.xander.atm.mapper.AccountMapper;
import com.xander.atm.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Autowired
    AccountMapper accountMapper;
    public int check(String id,String password){
        Account account = accountMapper.getAccountById(id);
        if(account == null){
            return 4;
        }
        if (account.getPassword().equals(password)){
            return 0;
        }
        else return 1;
    }
}
