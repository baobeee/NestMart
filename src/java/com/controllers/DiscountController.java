package com.controllers;

import com.models.Discount;
import com.models.DiscountDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/")
public class DiscountController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
   
    @Autowired
     private ServletContext servletContext;
    @Autowired
    DiscountDAO discountDAO;
    @RequestMapping(value = "discount", method = RequestMethod.GET)
    public String showDiscount (ModelMap model) {
        List<Discount> listDiscount = discountDAO.findAll();
        System.out.println(listDiscount); 
        model.addAttribute("listDiscount", listDiscount);
        return "/admin/discount"; 
    }

     // Display the form to create a new discount
    @RequestMapping(value = "discountCreate", method = RequestMethod.GET)
    public String showCreateDiscountForm(Model model) {
        model.addAttribute("discount", new Discount()); // Add an empty discount object
        return "/admin/discountCreate"; // View name for the create discount form
    }

    // Handle form submission to create a new discount
    @RequestMapping(value = "/discountCreate", method = RequestMethod.POST)
public String createDiscount(
    @ModelAttribute Discount discount,
    @RequestParam("imageFile") MultipartFile imageFile,
    Model model) {
    try {
        discountDAO.save(discount, imageFile, servletContext);
    } catch (Exception e) {
        e.printStackTrace();
        return "error";
    }
    return "redirect:/discount.htm";
}
    
 // Display the form to edit an existing discount
   @RequestMapping(value = "discountUpdate", method = RequestMethod.GET)
    public String showEditDiscountForm(@RequestParam("discountID") int id, Model model) {
        System.out.println("GET request received for discountUpdate with ID: " + id);

        Discount discount = discountDAO.findById(id);
    
        model.addAttribute("discount", discount);
        return "/admin/discountUpdate"; 
    }

  @RequestMapping(value = "/discountUpdate", method = RequestMethod.POST)
public String updateDiscount(@ModelAttribute Discount discount,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpServletRequest request,
                             Model model) {
    try {
        discountDAO.update(discount, imageFile, request.getServletContext());
    } catch (Exception e) {
        model.addAttribute("errorMessage", "Error updating discount: " + e.getMessage());
        return "errorPage"; // Redirect to an error page if needed
    }
    return "redirect:/discount.htm"; // Redirect to the discount list or a success page
}

    // Handle request to delete a discount
    @RequestMapping(value = "discountDelete", method = RequestMethod.GET)
    public String deleteDiscount(@RequestParam("discountID") int id) {
        discountDAO.deleteById(id); // Delete the discount
        return "redirect:/discount.htm"; // Redirect to the discount list page
    }

}
