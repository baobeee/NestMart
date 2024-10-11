package com.models;

import java.util.List;
import java.util.Map;

public interface CategoriesDAO {

    public List<Categories> findAll();

    void save(Categories category); 

    Categories findById(int id);

    void update(Categories category);

    void deleteById(int id);
        String getCategoryNameById(int id);
    Map<Integer, String> getCategoryNames(List<Integer> categoryIds);
    List<Categories> findPaginated(int page, int pageSize);
      List<Categories> searchByKeyword(String keyword);

    String findClosestMatch(String keyword);
               int levenshteinDistance(String a, String b);
    int getTotalCategories();
}


