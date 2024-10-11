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

    List<Offers> findAll();

    void save(Offers offers);

    Offers findById(int id);

    void update(Offers offer);

    void deleteById(int offerId);

    public boolean checkIfExists(Offers offers);

    List<Offers> findPaginated(int page, int pageSize);

    List<Offers> searchByKeyword(String keyword, int page, int pageSize);

    int countByKeyword(String keyword);

    int getTotalOffers();

    String findClosestMatch(String keyword);
}
