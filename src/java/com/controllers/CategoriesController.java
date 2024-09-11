package com.controllers;

import com.models.Categories;
import com.models.CategoriesDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class CategoriesController {
    @Autowired
    CategoriesDAO categoriesDAO;
    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String showCategories (ModelMap model) {
        List<Categories> listCategories = categoriesDAO.findAll();
        System.out.println(listCategories); 
        model.addAttribute("listCategories", listCategories);
        return "/admin/category"; 
    }
     // Display the form to create a new category
    @RequestMapping(value = "categoryCreate", method = RequestMethod.GET)
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Categories()); // Add an empty category object
        return "/admin/categoryCreate"; // View name for the create category form
    }

    // Handle form submission to create a new category
    @PostMapping(value = "categoryCreate")
    public String createCategory(@ModelAttribute("categories") Categories category) {
        categoriesDAO.save(category); // Save the new category
        return "redirect:/categories.htm"; // Redirect to the category list page
    }
    
 // Display the form to edit an existing category
    @RequestMapping(value = "categoryUpdate.htm", method = RequestMethod.GET)
    public String showEditCategoryForm(@RequestParam("categoryID") int id, Model model) {
            System.out.println("GET request received for categoryUpdate.htm with ID: " + id);

        Categories category = categoriesDAO.findById(id);
        model.addAttribute("category", category);
        return "/admin/categoryUpdate"; // View name for the edit category form
    }

    // Handle form submission to update an existing category
   @RequestMapping(value = "categoryUpdate.htm",  method = RequestMethod.POST)
public String updateCategory(@ModelAttribute("category") Categories category) {
    System.out.println("Updating category: " + category.getCategoryID() + ", " + category.getCategoryName());
    categoriesDAO.update(category);
    return "redirect:/categories.htm";
}
    // Handle request to delete a category
    @RequestMapping(value = "categoryDelete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam("categoryID") int id) {
        categoriesDAO.deleteById(id); // Delete the category
        return "redirect:/categories.htm"; // Redirect to the category list page
    }

}
