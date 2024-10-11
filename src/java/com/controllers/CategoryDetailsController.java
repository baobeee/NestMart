package com.controllers;

import com.models.Categories;
import com.models.CategoryDetails;
import com.models.CategoryDetailsDAO;
import com.models.CategoriesDAO;
import com.models.Products;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin")

public class CategoryDetailsController {

    @Autowired
    private CategoryDetailsDAO categoryDetailsDAO;
   @Autowired
    private CategoriesDAO categoriesDAO;

    @Autowired
    private ProductsDAO productsDAO;
 
@RequestMapping(value = "categoryDetail", method = RequestMethod.GET)
public String showCategoryDetails(ModelMap model, 
                                  @RequestParam(defaultValue = "1") int page, 
                                  @RequestParam(defaultValue = "12") int pageSize,
                                  @RequestParam(value = "keyword", required = false) String keyword) {

    List<CategoryDetails> listCategoryDetails;
    int totalCategoryDetails;

    if (keyword != null && !keyword.isEmpty()) {
        // Perform search by keyword
        listCategoryDetails = categoryDetailsDAO.searchByKeyword(keyword);
        totalCategoryDetails = listCategoryDetails.size();

        // Find the closest match for the keyword
        String closestMatch = categoryDetailsDAO.findClosestMatch(keyword);
        model.addAttribute("closestMatch", closestMatch);

        // Perform pagination for the search results
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalCategoryDetails);
        listCategoryDetails = listCategoryDetails.subList(fromIndex, toIndex);
    } else {
        // Get total count and paginated list for all category details
        totalCategoryDetails = categoryDetailsDAO.getTotalCategoryDetails();
        listCategoryDetails = categoryDetailsDAO.findPaginated(page, pageSize);
    }

    // Add attributes to the model
    model.addAttribute("listCategoryDetails", listCategoryDetails);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", (int) Math.ceil((double) totalCategoryDetails / pageSize));
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("keyword", keyword);
    
    return "/admin/categoryDetail";
}


    
@RequestMapping(value = "categoryDetailCreate", method = RequestMethod.GET)
public String showCategoryDetailsCreateForm(
        @RequestParam(value = "categoryID", required = false) Integer categoryID,
        ModelMap model) {

    List<Categories> listCategories = categoriesDAO.findAll();
    model.addAttribute("listCategories", listCategories);

    List<Products> listProducts = new ArrayList<>();
    if (categoryID != null) {
        listProducts = categoryDetailsDAO.findProductsByCategoryId(categoryID);
    }
    model.addAttribute("listProducts", listProducts);

    model.addAttribute("attributeCount", 1); 
    model.addAttribute("attributeCount", 1);  
    return "/admin/categoryDetailCreate";
}

@RequestMapping(value = "/categoryDetailCreate", method = RequestMethod.POST)
public String createCategoryDetails(
           @RequestParam("categoryID") int categoryID,
        @RequestParam(value = "productID", required = false) String productID,
        @RequestParam("attributeCount") int attributeCount,
        @RequestParam(value = "attributeName", required = false) List<String> attributeNames,
        @RequestParam(value = "attributeValue", required = false) List<String> attributeValues,
        Model model) {
    
    System.out.println("categoryID: " + categoryID);
    System.out.println("productID: " + productID);
    System.out.println("attributeCount: " + attributeCount);
    System.out.println("attributeNames: " + attributeNames);
    System.out.println("attributeValues: " + attributeValues);

    if (attributeNames == null || attributeValues == null || 
        attributeNames.isEmpty() || attributeValues.isEmpty() ||
        attributeNames.size() != attributeCount || attributeValues.size() != attributeCount) {
        model.addAttribute("errorMessage", "Please fill out all attribute names and values.");
        model.addAttribute("listCategories", categoriesDAO.findAll());
        model.addAttribute("listProducts", categoryDetailsDAO.findProductsByCategoryId(categoryID));
        model.addAttribute("attributeCount", attributeCount);
        return "/admin/categoryDetailCreate";
    }
  
    
    for (int i = 0; i < attributeCount; i++) {
        CategoryDetails categoryDetails = new CategoryDetails();
        categoryDetails.setCategoryID(categoryID);
        categoryDetails.setProductID(productID);
        categoryDetails.setAttributeName(attributeNames.get(i));
        categoryDetails.setAttributeValue(attributeValues.get(i));
        categoryDetailsDAO.save(categoryDetails);
    }
    
    return "redirect:/admin/categoryDetail.htm";
}

@RequestMapping(value = "/categoryDetailUpdate", method = RequestMethod.GET)
public String showCategoryDetailsUpdateForm(
        @RequestParam("categoryDetailID") int categoryDetailID,
        ModelMap model) {

    CategoryDetails categoryDetails = categoryDetailsDAO.findById(categoryDetailID);
    model.addAttribute("categoryDetails", categoryDetails);

    // Lấy danh sách categories và products để hiển thị trong form nếu cần
    List<Categories> listCategories = categoriesDAO.findAll();
    model.addAttribute("listCategories", listCategories);

    List<Products> listProducts = productsDAO.findByCategoryId(categoryDetails.getCategoryID());
    model.addAttribute("listProducts", listProducts);

    model.addAttribute("attributeCount", 1); 
    return "/admin/categoryDetailUpdate";
}

@RequestMapping(value = "/categoryDetailUpdate", method = RequestMethod.POST)
public String updateCategoryDetails(
           @RequestParam("categoryDetailID") int categoryDetailID,
           @RequestParam("attributeName") String attributeName,
           @RequestParam("attributeValue") String attributeValue,
           Model model) {

    try {
        // Lấy đối tượng CategoryDetails hiện tại
        CategoryDetails categoryDetails = categoryDetailsDAO.findById(categoryDetailID);

        // Cập nhật các thuộc tính cần thiết
        categoryDetails.setAttributeName(attributeName);
        categoryDetails.setAttributeValue(attributeValue);

        // Lưu thay đổi vào cơ sở dữ liệu
        categoryDetailsDAO.update(categoryDetails);

        return "redirect:/admin/categoryDetail.htm"; // Điều hướng đến trang danh sách category details
    } catch (Exception e) {
        model.addAttribute("error", "An error occurred while updating the category details.");
        return "/admin/categoryDetailUpdate"; // Trở về trang cập nhật với lỗi
    }
}

@RequestMapping(value = "categoryDetailDelete", method = RequestMethod.GET)
public String deleteCategoryDetail(@RequestParam("categoryDetailID") int id) {
    categoryDetailsDAO.deleteById(id); 
    return "redirect:/admin/categoryDetail.htm"; 
}



  
}
