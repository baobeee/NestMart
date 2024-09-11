/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Discount;
import com.models.DiscountDAO;
import com.models.Offers;
import com.models.OffersDAO;
import com.models.Products;
import com.models.ProductsDAO;
import java.text.SimpleDateFormat;
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

@Controller
@RequestMapping(value = "/")
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
    private ProductsDAO productsDAO;
    @Autowired
    private DiscountDAO discountDAO;

    @RequestMapping(value = "offers", method = RequestMethod.GET)
    public String showOffers(ModelMap model) {
        List<Offers> listOffers = offersDAO.findAll();
        System.out.println(listOffers);
        model.addAttribute("listOffers", listOffers);
        return "/admin/offers";
    }

    @RequestMapping(value = "offersCreate", method = RequestMethod.GET)
    public String showCreateOffersForm(Model model) {
        model.addAttribute("offers", new Offers());
        List<Products> listProducts = productsDAO.findAll();
        List<Discount> listDiscount = discountDAO.findAll();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listDiscount", listDiscount);
        return "/admin/offersCreate";
    }

    // Handle form submission to create a new offers
    @RequestMapping(value = "/offersCreate", method = RequestMethod.POST)
    public String createOffers(@ModelAttribute("offers") Offers offers) {
        offersDAO.save(offers);
        return "redirect:/offers.htm";
    }

   @RequestMapping(value = "offersUpdate", method = RequestMethod.GET)
public String showEditOffersForm(@RequestParam("offersID") int id, Model model) {
    System.out.println("GET request received for offersUpdate.htm with ID: " + id);
    List<Products> listProducts = productsDAO.findAll();
    List<Discount> listDiscount = discountDAO.findAll();
    model.addAttribute("listProducts", listProducts);
    model.addAttribute("listDiscount", listDiscount);
    Offers offers = offersDAO.findById(id);
    model.addAttribute("offers", offers);
    return "/admin/offersUpdate";
}

@RequestMapping(value = "/offersUpdate", method = RequestMethod.POST)
public String updateOffers(@ModelAttribute("offers") Offers offers) {
    offersDAO.update(offers);
    return "redirect:/offers.htm";
}


    @RequestMapping(value = "offersDelete", method = RequestMethod.GET)
    public String deleteOffers(@RequestParam("offersID") int id) {
        offersDAO.deleteById(id);
        return "redirect:/offers.htm";
    }

}
