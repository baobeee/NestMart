/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.util.List;

/**
 *
 * @author Acer
 */
public interface OffersDAO {
        public List<Offers> findAll();

    void save(Offers offers); 

    Offers findById(int id);

    void update(Offers offer);

    void deleteById(int id);
}
