package com.xander.atm.service;

import com.xander.atm.mapper.AccountMapper;
import com.xander.atm.mapper.StatementMapper;
import com.xander.atm.pojo.Account;
import com.xander.atm.pojo.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApiService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    StatementMapper statementMapper;

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

    public void save(double money, String id){
        Account account = accountMapper.getAccountById(id);
        account.setMoney(account.getMoney()+money);
        accountMapper.updateAccount(account);
        Statement statement = new Statement(id, 1, money);
        statementMapper.insertStatement(statement);
    }

    public int withdraw(double money, String id){
        Account account = accountMapper.getAccountById(id);
        double m = account.getMoney();
        if (money>m) return 1;
        account.setMoney(m-money);
        accountMapper.updateAccount(account);
        Statement statement = new Statement(id, 2, money);
        statementMapper.insertStatement(statement);
        return 0;
    }

    public int transfer(String accountId, double money, String toAccountId){
        Account toAccount = accountMapper.getAccountById(toAccountId);
        if (toAccount == null)return 2;
        Account account = accountMapper.getAccountById(accountId);
        if (account.getMoney()<money)return 1;
        account.setMoney(account.getMoney()-money);
        toAccount.setMoney(toAccount.getMoney()+money);
        accountMapper.updateAccount(account);
        accountMapper.updateAccount(toAccount);
        Statement statement = new Statement(accountId, 3, money, toAccountId);
        statementMapper.insertStatement(statement);
        return 0;
    }

    public int change(String id, String old, String newPwd){
        Account account = accountMapper.getAccountById(id);
        if (account == null) return 1;
        if (!account.getPassword().equals(old))return 2;
        account.setPassword(newPwd);
        accountMapper.updateAccount(account);
        return 0;
    }
}
