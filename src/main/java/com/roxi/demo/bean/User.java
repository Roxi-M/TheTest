package com.roxi.demo.bean;

import lombok.Data;

@Data
public class User {
    private String account;
    private String password;
    private String stuCode;
    private int id;
    public User(){

    }
    public User(String account,String password){
        this.account=account;
        this.password=password;
    }

}
