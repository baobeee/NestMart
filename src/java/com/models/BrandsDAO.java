package com.models;

import java.util.List;

public interface BrandsDAO {

    public List<Brands> findAll();

    void save(Brands brand);

    Brands findById(int id);

    void update(Brands brand);

void deleteById(int id);
    String getBrandNameById(int id);

    int getTotalBrands();

    List<Brands> findPaginated(int page, int pageSize);

    List<Brands> searchByKeyword(String keyword);

    String findClosestMatch(String keyword);

    int levenshteinDistance(String a, String b);

}
