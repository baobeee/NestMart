/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.services.SessionService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Win10
 */
@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/shipper/index", method = RequestMethod.GET)
    public String ShowEmployeeIndex(HttpServletRequest request, Model model) {
        if (!sessionService.isSessionValid(request)) {
            return "redirect:/login.htm";
        }
        return "/shipper/index";
    }
}
