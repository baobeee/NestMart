/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.util.List;

/**
 *
 * @author Win10
 */
public interface SalaryDAO {
    void save(Salary salary);
    Salary findById(int salaryID);
    void update(Salary salary);
    void delete(int salaryID);
    List<Salary> getAllSalaries();
    List<Salary> getSalariesByAccountId(int accountID);

}
