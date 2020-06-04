package com.xander.atm.pojo;


import lombok.Data;

@Data
public class Statement {
    private int id;
    private String accountId;
    private String time;
    private int type;
    private double money;
    private String toAccount;
}
