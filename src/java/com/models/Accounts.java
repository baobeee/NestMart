package com.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Accounts {
    private int accountID;
    private String phoneNumber;
    private String password;
    private String email;
    private int role;
    private String gender;
    private String fullName;
    private Date birthday;
    private String address;
    private BigDecimal hourlyRate;
    
    private String RoleName;

    public Accounts() {
        this.accountID = 0;
        this.phoneNumber = "";
        this.password = "";
        this.email = "";
        this.role = 4;
        this.gender = "";
        this.fullName = "";
        this.birthday = null;
        this.address = "";
        this.hourlyRate = BigDecimal.ZERO;
    }

    public Accounts(int accountID, String phoneNumber, String password, String email, 
                   int role, String gender, String fullName, Date birthday, String address, BigDecimal hourlyRate) {
        this.accountID = accountID;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.fullName = fullName;
        this.birthday = birthday;
        this.address = address;
        this.hourlyRate = hourlyRate;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
}
