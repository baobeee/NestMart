/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.AttributeDetail;
import com.models.Brands;
import com.models.BrandsDAO;
import com.models.Categories;
import com.models.CategoriesDAO;
import com.models.CategoryDetails;
import com.models.CategoryDetailsDAO;
import com.models.Feedback;
import com.models.FeedbackDAO;
import com.models.ProductsClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.models.ProductsClientDAO;

/**
 *
 * @author Acer
 */
@Controller
@RequestMapping("/client")

public class ProductClientController {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ProductsClientDAO productsDAO;
    @Autowired
    private CategoryDetailsDAO categoryDetailsDAO;

    @Autowired
    private CategoriesDAO categoriesDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private BrandsDAO brandsDAO;

@RequestMapping(value = "product", method = RequestMethod.GET)
public String showProducts(ModelMap model,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "8") int pageSize,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value  = "categoryID", required = false) Integer categoryID) {
    
    if (keyword == null || keyword.trim().isEmpty()) {
        keyword = "";
    } else {
        keyword = keyword.trim();
    }
    
    List<ProductsClient> listProducts;
    String closestMatch = null;
    List<Categories> listCategories = categoriesDAO.findAll();
    model.addAttribute("listCategories", listCategories);
    
    int totalProducts;
    
    if (categoryID != null) {
        listProducts = productsDAO.findByCategory(categoryID, page, pageSize);
        totalProducts = productsDAO.countByCategory(categoryID);
    } else if (!keyword.isEmpty()) {
        listProducts = productsDAO.searchByProductName(keyword, page, pageSize);
        totalProducts = productsDAO.countByKeyword(keyword);
        
        System.out.println("Search results for '" + keyword + "': " + listProducts.size());
        
        if (listProducts.isEmpty()) {
            closestMatch = productsDAO.findClosestMatch(keyword);
            System.out.println("Closest match for '" + keyword + "': " + closestMatch);
        }
    } else {
        listProducts = productsDAO.findPaginated(page, pageSize);
        totalProducts = productsDAO.getTotalProducts();
    }

    // In ra số lượng sản phẩm
    System.out.println("Total Products: " + totalProducts);
    
    // Tính toán số trang
    int totalPages = (totalProducts + pageSize - 1) / pageSize;
    
    System.out.println("Current Page: " + page);
    System.out.println("Total Pages: " + totalPages);

    // Tạo danh sách tên danh mục và tên thương hiệu cho các sản phẩm
    Map<Integer, String> categoryNames = new HashMap<>();
    Map<Integer, String> brandNames = new HashMap<>();

    for (ProductsClient product : listProducts) {
        int categoryId = product.getCategoryID();
        if (!categoryNames.containsKey(categoryId)) {
            String categoryName = categoriesDAO.getCategoryNameById(categoryId);
            categoryNames.put(categoryId, categoryName);
        }

        int brandId = product.getBrandID();
        if (!brandNames.containsKey(brandId)) {
            String brandName = brandsDAO.getBrandNameById(brandId);
            brandNames.put(brandId, brandName);
        }
    }    
    model.addAttribute("listProducts", listProducts);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("keyword", keyword);
    model.addAttribute("closestMatch", closestMatch);
    model.addAttribute("categoryID", categoryID);
    
    return "/client/product";
}



    @RequestMapping("/productDetails")
    public String showProductDetails(@RequestParam("productID") String id,
            @RequestParam(value = "starFilter", required = false) Integer starFilter,
            Model model) {
        // Kiểm tra nếu id là null hoặc rỗng
        if (id == null || id.isEmpty()) {
            // Xử lý lỗi hoặc trả về trang lỗi
            return "errorPage"; // Hoặc trang lỗi phù hợp
        }

        ProductsClient product = productsDAO.findById(id);
        List<Categories> listCategories = categoriesDAO.findAll();
        List<Brands> listBrands = brandsDAO.findAll();
        List<ProductsClient> listProducts = productsDAO.select8random();

        // Sử dụng bộ lọc số sao để lấy phản hồi
        List<Feedback> feedbacks;
        if (starFilter != null && starFilter > 0) {
            feedbacks = feedbackDAO.getFeedbacksByRating(id, starFilter);
        } else {
            feedbacks = feedbackDAO.getFeedbacksForProduct(id);
        }

        Map<String, Object> ratingData = feedbackDAO.getAverageRatingAndCount(id);
        double averageRating = (double) ratingData.getOrDefault("averageRating", 0.0);
        int feedbackCount = (int) ratingData.getOrDefault("feedbackCount", 0);
        List<AttributeDetail> categoryDetailsList = categoryDetailsDAO.findByProductId(id);

        model.addAttribute("categoryDetailsList", categoryDetailsList);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listBrands", listBrands);
        model.addAttribute("product", product);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("feedbackCount", feedbackCount);

        return "/client/productDetails";
    }
}
