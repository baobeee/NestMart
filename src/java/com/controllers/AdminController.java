package com.controllers;

import com.config.SecurityConfig;
//import com.services.SessionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    
//    @Autowired
//    private SessionService sessionService;
    
//    @RequestMapping("/**")
//    public String checkSessionAndRedirect(HttpSession session, HttpServletRequest request) {
//        Integer role = (Integer) session.getAttribute("role");
//        if (role == null) {
//            return "redirect:/login.htm";
//        }
//
//        // Check role for admin-specific pages
//        if (role != 1) {
//            return "redirect:/index.htm"; // Redirect if the user is not an admin
//        }
//
//        return request.getRequestURI(); // Proceed to the original requested URI
//    }

    
    @RequestMapping("/brand")
    public String showBrandPage() {
        return "/admin/brands.htm"; 
    }

    @RequestMapping("/category")
    public String showCategoryPage() {
        return "/admin/products.htm"; 
    }
       @RequestMapping("/product")
    public String showProductPage() {
        return "/admin/products.htm"; 
    }
        @RequestMapping("/categoryDetail")
    public String showCategoryDetailPage() {
        return "/admin/categoryDetails.htm"; 
    }
    
    @RequestMapping("/discount")
    public String showDiscountPage() {
        return "/admin/discount.htm"; 
    }
    
    @RequestMapping("/offers")
    public String showOfferPage() {
        return "/admin/offers.htm"; 
    }
    
    @RequestMapping("/account")
    public String showUserAccountPage() {
        return "/admin/account.htm"; 
    }
    
    @RequestMapping("/inventory")
    public String showInventoryPage() {
        return "/admin/inventory.htm"; 
    }
    
    @RequestMapping("/notifications")
    public String showNotificationPage() {
        return "/admin/notifications.htm"; 
    }
}
