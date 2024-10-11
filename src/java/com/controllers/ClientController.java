package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.ProductsClient;
import com.models.ProductsClientDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private AccountsDAO accountsDAO;
    @Autowired
    private ProductsClientDAO productClientDAO;

    // Display client index page
    @RequestMapping(value = "/clientboard", method = RequestMethod.GET)
    public String showCustomerIndex(HttpSession session, Model model) {
        List<ProductsClient> listProducts = productClientDAO.select8random();
        model.addAttribute("listProducts", listProducts);
        return "/client/clientboard";
    }
   @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAbout(HttpSession session, Model model) {
      
        return "/client/about";
    }

//    // Show account information
//    @RequestMapping(value = "/accountInformation", method = RequestMethod.GET)
//    public String showAccountInformation(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("email");
//        if (email == null) {
//            model.addAttribute("error", "You must be logged in to view account information.");
//            return "redirect:/login.htm";
//        }
//
//        Accounts account = accountsDAO.findByEmail(email);
//        if (account == null) {
//            model.addAttribute("error", "Account not found.");
//            return "redirect:/login.htm";
//        }
//
//        model.addAttribute("account", account);
//        return "/client/accountInformation";
//    }
//
//    // Update account information
//    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST)
//    public String updateAccountInformation(@ModelAttribute("account") Accounts account, RedirectAttributes redirectAttributes) {
//        Accounts existingAccount = accountsDAO.findById(account.getAccountID());
//
//        if (existingAccount != null) {
//            existingAccount.setEmail(account.getEmail());
//            existingAccount.setGender(account.getGender());
//            existingAccount.setPhoneNumber(account.getPhoneNumber());
//            existingAccount.setAddress(account.getAddress());
//            existingAccount.setFullName(account.getFullName());
//
//            // Save changes to the database
//            accountsDAO.update(existingAccount);
//            redirectAttributes.addFlashAttribute("message", "Account information updated successfully.");
//        } else {
//            redirectAttributes.addFlashAttribute("error", "Failed to update account information.");
//        }
//
//        return "redirect:/client/account.htm";
//    }
}
