package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.Orders;
import com.models.OrdersDAO;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shipper")
public class ShippersController {

    @Autowired
    private AccountsDAO accountsDAO;
    @Autowired
    private OrdersDAO orderDAO;

    // Display the main page for shipper including a list of orders
    @RequestMapping("/shippers")
    public String showShipperPage(@RequestParam(value = "orderID", required = false) String orderIDQuery,
            @RequestParam(value = "search", required = false) String searchQuery,
            @RequestParam(value = "status", required = false) String status,
            Model model,
            HttpSession session) {
        // Check if the session is valid and the user is logged in
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Retrieve accountId from the session (assuming it's used for the shipper)
        Integer accountId = (Integer) session.getAttribute("accountId");
        if (accountId == null) {
            return "redirect:/login.htm"; // Handle the case where accountId is not found in the session
        }

        // Retrieve and filter orders based on accountId (acting as shipperID), order ID, search query, and status
        List<Orders> orders = orderDAO.searchAndFilterOrders(accountId, orderIDQuery, searchQuery, status);

        // Add attributes to the model to be used in the view
        model.addAttribute("orders", orders);
        model.addAttribute("orderID", orderIDQuery);
        model.addAttribute("search", searchQuery);
        model.addAttribute("status", status);
        model.addAttribute("accountId", accountId);

        // Return the view for /WEB-INF/jsp/shipper/shippers.jsp
        return "/shipper/shippers";
    }

    @RequestMapping(value = "/approveReturn", method = RequestMethod.POST)
    @ResponseBody
    public String approveReturn(@RequestParam("orderID") int orderID,
            HttpSession session) {
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        try {
            orderDAO.updateOrder(orderID, "Approved", null);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/confirmReturnPickup", method = RequestMethod.POST)
    @ResponseBody
    public String confirmReturnPickup(@RequestParam("orderID") int orderID,
            HttpSession session) {
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        try {
            orderDAO.updateOrder(orderID, "Return Completed", null);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    // Update the order status
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public String updateOrderStatus(@RequestParam("orderID") int orderID,
            @RequestParam("status") String status,
            @RequestParam("paymentMethod") String paymentMethod,
            HttpSession session) {
        // Check if the session is valid and the user is logged in
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        try {
            orderDAO.updateOrder(orderID, status, paymentMethod);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    // Handle accepting an order for delivery
    @RequestMapping("/acceptOrder")
    public String acceptOrder(@RequestParam("orderID") int orderID,
            @RequestParam("shipperID") int shipperID,
            HttpSession session) {
        // Check if the session is valid and the user is logged in
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Set the order status to 'Delivering' and update the transaction status
        orderDAO.updateOrder(orderID, "Delivering", "Cash on Delivery");
        return "redirect:/shipper/shippers/" + shipperID + ".htm"; // Redirect back to shipper page with updated orders list
    }

    // Confirm order completion
    @RequestMapping("/completeOrder")
    public String completeOrder(@RequestParam("orderID") int orderID,
            @RequestParam("shipperID") int shipperID,
            HttpSession session) {
        // Check if the session is valid and the user is logged in
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Set the order status to 'Completed' and update the transaction status
        orderDAO.updateOrder(orderID, "Completed", "Cash on Delivery");
        return "redirect:/shipper/shippers/" + shipperID + ".htm"; // Redirect back to shipper page with updated orders list
    }
}
