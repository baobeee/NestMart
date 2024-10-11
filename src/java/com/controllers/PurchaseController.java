package com.controllers;

import com.models.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")

public class PurchaseController {

    @GetMapping("/purchase")
    public String proceedToCheckout(HttpSession session, RedirectAttributes redirectAttributes) {
        String fullName = (String) session.getAttribute("fullName");

        if (fullName == null || fullName.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to purchase items.");
            return "redirect:/login.htm";
        }
        return "client/purchase";
    }

}
