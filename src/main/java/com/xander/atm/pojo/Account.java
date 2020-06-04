package com.xander.atm.pojo;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String name;
    private String password;
    private double money;
}
