package com.bjpowernode.bean;

import java.io.Serializable;

/*
    系统管理员
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = -6849794667710L;

    private String userName;
    private String password;

    public Admin(){

    }
    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Admin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
