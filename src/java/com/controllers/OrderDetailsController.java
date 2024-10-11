/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.AssignShipper;
import com.models.OrderDetailsDTO;
import com.models.OrderDetailsDTODAO;
import com.models.Orders;
import com.models.OrdersDAO;
import com.models.OrdersDTO;
import com.models.OrdersDTODAO;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Acer
 */
@Controller
@RequestMapping(value = "/employee")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsDTODAO orderDetailsDTODAO;

    @Autowired
    private OrdersDTODAO orderDTODAO;
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private AccountsDAO accountsDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

   @RequestMapping(value = "assignShipper", method = RequestMethod.GET)
public String showAssignShipperForm(@RequestParam("orderID") int id, Model model) {
    OrdersDTO order = orderDTODAO.findByOrderId(id);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedOrderDate = dateFormat.format(order.getOrderDate());
    List<Accounts> shippers = accountsDAO.findShippers();
    List<OrderDetailsDTO> orderDetails = orderDetailsDTODAO.findByOrderId(id);
    model.addAttribute("orderDetails", orderDetails);
    model.addAttribute("order", order);
    model.addAttribute("formattedOrderDate", formattedOrderDate); 
    model.addAttribute("shippers", shippers);
    return "employee/assignShipper";
}


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String assignNewShipper(@ModelAttribute("assignShipper") AssignShipper assignShipper) {
        // Xử lý logic cập nhật shipperID và status
        System.out.println("Updating order with OrderID: " + assignShipper.getOrderID() + " and ShipperID: " + assignShipper.getShipperID());
        ordersDAO.updateShipperAndStatus(assignShipper.getOrderID(), assignShipper.getShipperID());
        return "redirect:/employee/order.htm";
    }

    @RequestMapping(value = "viewOrder", method = RequestMethod.GET)
    public String viewAssignShipperForm(@RequestParam("orderID") int id, Model model) {
        List<OrderDetailsDTO> orderDetails = orderDetailsDTODAO.findByOrderId(id);
        OrdersDTO order = orderDTODAO.findByOrderId(id);
        Accounts shipper = accountsDAO.findShipperById(order.getOrderID());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("order", order);
        model.addAttribute("shipper", shipper);
        return "employee/viewOrder";
    }

}
