package com.xander.atm.pojo;


import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Statement {
    private int id;
    private String accountId;
    private String time;
    private int type;
    private double money;
    private String toAccount;

    public Statement(int id, String accountId, String time, int type, double money, String toAccount) {
        this.id = id;
        this.accountId = accountId;
        this.time = time;
        this.type = type;
        this.money = money;
        this.toAccount = toAccount;
    }

    public Statement(String accountId, int type, double money, String toAccount){
        this.id = 0;
        this.accountId = accountId;
        this.type = type;
        this.money = money;
        this.toAccount = toAccount;
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");
        this.time = df.format(new Date());
    }

    public Statement(String accountId, int type, double money){
        this.id = 0;
        this.accountId = accountId;
        this.type = type;
        this.money = money;
        this.toAccount = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");
        this.time = df.format(new Date());
    }
}
