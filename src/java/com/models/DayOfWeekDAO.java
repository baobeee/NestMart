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
public interface DayOfWeekDAO {
    void save(DayOfWeek dayOfWeek);
    DayOfWeek findById(int dayID);
    List<DayOfWeek> findAll();
    void update(DayOfWeek dayOfWeek);
    void delete(int dayID);
}
    