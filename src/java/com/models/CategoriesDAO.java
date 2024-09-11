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


}
