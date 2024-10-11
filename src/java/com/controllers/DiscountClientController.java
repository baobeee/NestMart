/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Discount;
import com.models.DiscountClient;
import com.models.DiscountClientDAO;
import com.models.ProductsClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Acer
 */
@Controller
@RequestMapping("/client")
public class DiscountClientController {
    @Autowired
    private DiscountClientDAO discountClientDAO;

   @RequestMapping("/discount")
    public String listDiscounts(Model model) {
        List<DiscountClient> listDiscounts = discountClientDAO.getAllDiscountsWithProducts();
        model.addAttribute("listDiscounts", listDiscounts);
        return "/client/discount";
    }


   @RequestMapping(value = "/discountinfo", method = RequestMethod.GET)
public String showDiscountProducts(
    @RequestParam("discountID") int discountID,
    @RequestParam(value = "page", defaultValue = "1") int currentPage,
    @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
    ModelMap model) {
    
    // Tính tổng số sản phẩm và số trang
    int totalProducts = discountClientDAO.countProductsByDiscount(discountID);
    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
    
    // Lấy danh sách sản phẩm theo discountID và phân trang
    List<ProductsClient> listProducts = discountClientDAO.findProductsByDiscount(discountID, currentPage, pageSize);

    // Đưa danh sách sản phẩm và các thông số phân trang vào model
    model.addAttribute("listProducts", listProducts);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("discountID", discountID); 

    // Trả về tên trang JSP để hiển thị sản phẩm
    return "client/discountinfo";
}

}
