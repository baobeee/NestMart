package com.controllers;

import com.models.Brands;
import com.models.BrandsDAO;
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
public class BrandsController {

    @Autowired
    BrandsDAO brandsDAO;

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    public String showBrands(ModelMap model) {
        List<Brands> listBrands = brandsDAO.findAll();
        System.out.println(listBrands);
        model.addAttribute("listBrands", listBrands);
        return "/admin/brand"; 
    }

    // Display the form to create a new brand
    @RequestMapping(value = "brandCreate", method = RequestMethod.GET)
    public String showCreateBrandForm(Model model) {
        model.addAttribute("brand", new Brands()); 
        return "/admin/brandCreate"; 
    }

    // Handle form submission to create a new brand
    @PostMapping(value = "brandCreate")
    public String createBrand(@ModelAttribute("brand") Brands brand) {
        brandsDAO.save(brand);
        return "redirect:/brand.htm"; 
    }

    // Display the form to edit an existing brand
    @RequestMapping(value = "brandUpdate.htm", method = RequestMethod.GET)
    public String showEditBrandForm(@RequestParam("brandID") int id, Model model) {
        System.out.println("GET request received for brandUpdate.htm with ID: " + id);

        Brands brand = brandsDAO.findById(id);
        model.addAttribute("brand", brand);
        return "/admin/brandUpdate"; 
    }

    // Handle form submission to update an existing brand
    @RequestMapping(value = "brandUpdate.htm", method = RequestMethod.POST)
    public String updateBrand(@ModelAttribute("brand") Brands brand) {
        System.out.println("Updating brand: " + brand.getBrandID() + ", " + brand.getBrandName());
        brandsDAO.update(brand);
        return "redirect:/brand.htm"; 
    }

    // Handle request to delete a brand
    @RequestMapping(value = "brandDelete", method = RequestMethod.GET)
    public String deleteBrand(@RequestParam("brandID") int id) {
        brandsDAO.deleteById(id);
        return "redirect:/brand.htm"; 
    }
}
