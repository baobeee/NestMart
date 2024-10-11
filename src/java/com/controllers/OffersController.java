/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Discount;
import com.models.DiscountDAO;
import com.models.Offers;
import com.models.OffersDAO;
import com.models.ProductsClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.models.ProductsClientDAO;

@Controller
@RequestMapping(value = "/admin")
public class OffersController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @Autowired
    OffersDAO offersDAO;
    @Autowired
    private ProductsClientDAO productsDAO;
    @Autowired
    private DiscountDAO discountDAO;

    @RequestMapping(value = "offers", method = RequestMethod.GET)
    public String showOffers(
            ModelMap model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }

        List<Offers> listOffers;
        String closestMatch = null;

        if (!keyword.isEmpty()) {
     
            listOffers = offersDAO.searchByKeyword(keyword, page, pageSize);

         
            if (listOffers.isEmpty()) {
                closestMatch = offersDAO.findClosestMatch(keyword);
            }
        } else {
           
            listOffers = offersDAO.findPaginated(page, pageSize);
        }

        int totalOffers;
        if (keyword.isEmpty()) {
            totalOffers = offersDAO.getTotalOffers();
        } else {
            totalOffers = offersDAO.countByKeyword(keyword);
        }

        int totalPages = (int) Math.ceil((double) totalOffers / pageSize);

        model.addAttribute("listOffers", listOffers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword); 

        if (closestMatch != null) {
            model.addAttribute("closestMatch", closestMatch);
        }

        return "/admin/offers";
    }

    @RequestMapping(value = "offersCreate", method = RequestMethod.GET)
    public String showCreateOffersForm(Model model) {
        model.addAttribute("offers", new Offers());
        List<ProductsClient> listProducts = productsDAO.findAll();
        List<Discount> listDiscount = discountDAO.findAll();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listDiscount", listDiscount);
        return "/admin/offersCreate";
    }
@RequestMapping(value = "/offersCreate", method = RequestMethod.POST)
public String createOffers(@ModelAttribute("offers") Offers offers, Model model) {
    if (offersDAO.checkIfExists(offers)) {
        model.addAttribute("error1", "This product with this discount has existed.");
        model.addAttribute("offers", offers); 
        return "admin/offersCreate";  
    }

    try {
        offersDAO.save(offers);
    } catch (RuntimeException e) {
        model.addAttribute("errorDiscount", e.getMessage()); 
        model.addAttribute("offers", offers);
        return "admin/offersCreate"; 
    }

    return "redirect:/admin/offers.htm"; 
}





    @RequestMapping(value = "offersUpdate", method = RequestMethod.GET)
    public String showEditOffersForm(@RequestParam("offersID") int id, Model model) {
        System.out.println("GET request received for offersUpdate.htm with ID: " + id);
        List<ProductsClient> listProducts = productsDAO.findAll();
        List<Discount> listDiscount = discountDAO.findAll();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listDiscount", listDiscount);
        Offers offers = offersDAO.findById(id);
        model.addAttribute("offers", offers);
        return "/admin/offersUpdate";
    }

    @RequestMapping(value = "/offersUpdate", method = RequestMethod.POST)
    public String updateOffers(@ModelAttribute("offers") Offers offers, Model model) {
         
        offersDAO.update(offers);
        return "redirect:/admin/offers.htm";
    }

    @RequestMapping(value = "offersDelete", method = RequestMethod.GET)
    public String deleteOffers(@RequestParam("offersID") int id) {
        offersDAO.deleteById(id);
        return "redirect:/admin/offers.htm";
    }

}
