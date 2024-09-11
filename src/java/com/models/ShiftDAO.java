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
public interface ShiftDAO {

    void save(Shift shift);

    Shift findById(int shiftID);

    List<Shift> findAll();

    void update(Shift shift);

    void delete(int shiftID);


}
