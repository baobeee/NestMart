package com.controllers;

import com.models.CategoryDetails;
import com.models.CategoryDetailsDAO;
import com.models.CategoriesDAO;
import com.models.ProductsDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Controller
public class CategoryDetailsController {

    @Autowired
    private CategoryDetailsDAO categoryDetailsDAO;

 
 
    @RequestMapping(value = "categoryDetails", method = RequestMethod.GET)
    public String showCategoryDetails(ModelMap model) {
          List<CategoryDetails> listCategoryDetails = categoryDetailsDAO.findAll();
        System.out.println(listCategoryDetails); 
        model.addAttribute("listCategoryDetails", listCategoryDetails);
        return "/admin/categoryDetail";
    }
}
