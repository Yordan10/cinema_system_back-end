package com.example.cinemasystem.model;

import com.example.cinemasystem.serviceInterfaces.IAccount;

public class UserAccount extends  User implements IAccount {
    public UserAccount(int id,String username, String password,String email, String firstName, String lastName, String role)
    {
        super(id,username,password,email,firstName,lastName,role);

    }
    public UserAccount(){}
}
