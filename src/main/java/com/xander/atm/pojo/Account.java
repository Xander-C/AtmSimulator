package com.xander.atm.pojo;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String name;
    private String password;
    private double money;

    public Account(String id, String name, String password, double money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
    }
}
