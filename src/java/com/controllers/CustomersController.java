/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Win10
 */
@Controller
@RequestMapping("/customer")
public class CustomersController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String ShowCustomerIndex(Model model){
       return "/customer/index";
    }
}
