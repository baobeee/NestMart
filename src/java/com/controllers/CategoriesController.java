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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin")
public class CategoriesController {
    @Autowired
    CategoriesDAO categoriesDAO;
    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String showCategories(ModelMap model, 
                                 @RequestParam(defaultValue = "1") int page, 
                                 @RequestParam(defaultValue = "12") int pageSize, 
                                 @RequestParam(required = false) String keyword) {
        int totalCategories = categoriesDAO.getTotalCategories();
        int totalPages = (int) Math.ceil((double) totalCategories / pageSize);

        List<Categories> listCategories;
        String closestMatch = null;
        if (keyword != null && !keyword.trim().isEmpty()) {
            listCategories = categoriesDAO.searchByKeyword(keyword);
            // Find the closest match for typo-tolerance
            closestMatch = categoriesDAO.findClosestMatch(keyword);
        } else {
            listCategories = categoriesDAO.findPaginated(page, pageSize);
        }

        model.addAttribute("listCategories", listCategories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword);
        model.addAttribute("closestMatch", closestMatch);

        return "/admin/category";
    }

    @RequestMapping(value = "categoryCreate", method = RequestMethod.GET)
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Categories()); 
        return "/admin/categoryCreate"; 
    }

    @PostMapping(value = "categoryCreate")
    public String createCategory(@ModelAttribute("categories") Categories category) {
        categoriesDAO.save(category); 
        return "redirect:/admin/categories.htm";
    }
    
    @RequestMapping(value = "categoryUpdate", method = RequestMethod.GET)
    public String showEditCategoryForm(@RequestParam("categoryID") int id, Model model) {
            System.out.println("GET request received for categoryUpdate.htm with ID: " + id);

        Categories category = categoriesDAO.findById(id);
        model.addAttribute("category", category);
        return "/admin/categoryUpdate";
    }

   @RequestMapping(value = "categoryUpdate",  method = RequestMethod.POST)
public String updateCategory(@ModelAttribute("category") Categories category) {
    System.out.println("Updating category: " + category.getCategoryID() + ", " + category.getCategoryName());
    categoriesDAO.update(category);
    return "redirect:/admin/categories.htm";
}
        @RequestMapping(value = "categoryDelete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam("categoryID") int id, RedirectAttributes redirectAttributes) {
        try {
            categoriesDAO.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/categories.htm";
    }

}
