package com.xander.atm.service;

import com.xander.atm.mapper.AccountMapper;
import com.xander.atm.mapper.StatementMapper;
import com.xander.atm.pojo.Account;
import com.xander.atm.pojo.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    StatementMapper statementMapper;

    public Account getAccountById(String id){return accountMapper.getAccountById(id);}

    public List<Statement> getStatements(String id){return statementMapper.getStatementById(id);}
}
