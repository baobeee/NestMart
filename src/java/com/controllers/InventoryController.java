package com.controllers;

import com.models.Inventory;
import com.models.InventoryDAO;
import com.models.Products;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class InventoryController {

    @Autowired
    InventoryDAO inventoryDAO;

    @RequestMapping(value = "inventory", method = RequestMethod.GET)
    public String showInvetory(ModelMap model) {
        List<Inventory> inventoryList = inventoryDAO.findAllInventory();
        model.addAttribute("inventoryList", inventoryList);
        return "/admin/inventory";
    }

    @ModelAttribute("addInventoryForm")
    public Inventory inventoryForm() {
        return new Inventory();
    }

    @RequestMapping(value = "addInventoryForm", method = RequestMethod.GET)
    public String showInventoryForm(ModelMap model) {
        List<Products> productList = inventoryDAO.findAllProducts();
        model.addAttribute("productList", productList);
        return "/admin/inventoryCreate";
    }

    @RequestMapping(value = "addInventory", method = RequestMethod.POST)
    public String createInventory(@ModelAttribute("inventoryCreate") @Valid Inventory inventory, BindingResult br) {
        if (br.hasErrors()) {
            return "/admin/inventoryCreate";
        }

        if (inventory.getLastUpdated() == null) {
            LocalDateTime now = LocalDateTime.now();
            inventory.setLastUpdated(now);
        }

        Inventory existingInventory = inventoryDAO.get(inventory.getProductID());
        if (existingInventory != null) {
            int newQuantity = existingInventory.getQuantity() + inventory.getQuantity();
            existingInventory.setQuantity(newQuantity);
            existingInventory.setLastUpdated(inventory.getLastUpdated());
            inventoryDAO.update(existingInventory);
        } else {
            inventoryDAO.add(inventory);
        }

        return "redirect:inventory.htm";
    }

    @ModelAttribute("editInventoryForm")
    public Inventory editInventoryForm() {
        return new Inventory();
    }

    @RequestMapping(value = "updateInventory", method = RequestMethod.GET)
    public String updateInventoryForm(@RequestParam("productID") String productID, ModelMap model) {
        Inventory inventory = inventoryDAO.get(productID);
        model.addAttribute("inventory", inventory);
        return "/admin/inventoryUpdate";
    }
    
    @RequestMapping(value = "editInventory", method = RequestMethod.POST)
    public String updateInventory(@ModelAttribute("inventoryUpdate") @Valid Inventory inventory, BindingResult br) {
        if (br.hasErrors()) {
            return "/admin/inventoryUpdate";
        }

        if (inventory.getLastUpdated() == null) {
            LocalDateTime now = LocalDateTime.now();
            inventory.setLastUpdated(now);
        }
        
        inventoryDAO.update(inventory);

        return "redirect:inventory.htm";
    }

    @RequestMapping(value = "deleteInventory", method = RequestMethod.GET)
    public String deleteInventory(@RequestParam("productID") String productID) {
        inventoryDAO.delete(productID);
        return "redirect:inventory.htm";
    }
    
    @RequestMapping(value = "findInventory", method = RequestMethod.GET)
    public String findInventory(String keyword, ModelMap model){
        List<Inventory> inventoryList = inventoryDAO.findInventory(keyword);
        model.addAttribute("inventoryList", inventoryList);
        return "/admin/inventory";
    }
}
