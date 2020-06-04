package com.xander.atm.service;

import com.xander.atm.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    @Autowired
    AccountMapper accountMapper;
}
