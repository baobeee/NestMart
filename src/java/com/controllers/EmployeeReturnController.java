/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;
import com.models.Orders;
import com.models.OrdersDAO;
import com.models.ReturnRequestResponse;
import com.models.ReturnRequestResponseDAO;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/employee")
public class EmployeeReturnController {
    @Autowired
    private OrdersDAO ordersDAO;
    
    @Autowired
    private ReturnRequestResponseDAO returnRequestResponseDAO;
   @GetMapping("/returnRequests")
public String listReturnRequests(Model model) {
    List<Orders> returnRequests = returnRequestResponseDAO.getOrdersWithReturnRequests();
        List<Orders> completedReturns = returnRequestResponseDAO.getCompletedReturnOrders();

    model.addAttribute("returnRequests", returnRequests);
        model.addAttribute("completedReturns", completedReturns);
        
    return "/employee/returnRequests";
}
@PostMapping("/process")
public String processReturnRequest(
    @RequestParam(value = "orderID", required = false) String orderIDString,
    @RequestParam("message") String message,
    @RequestParam("newOrderStatus") String newOrderStatus,
    @RequestParam(value = "newReturnRequestStatus", required = false) String newReturnRequestStatus,
    HttpSession session,
    RedirectAttributes redirectAttributes) {
    
    System.out.println("Received parameters:");
    System.out.println("orderID: " + orderIDString);
    System.out.println("message: " + message);
    System.out.println("newOrderStatus: " + newOrderStatus);
    System.out.println("newReturnRequestStatus: " + newReturnRequestStatus);

    int orderID;
    try {
        orderID = Integer.parseInt(orderIDString);
    } catch (NumberFormatException e) {
        System.out.println("Invalid orderID");
        redirectAttributes.addFlashAttribute("error", "Invalid order ID");
        return "redirect:/employee/returnRequests.htm";
    }

    Integer employeeId = (Integer) session.getAttribute("accountId");
    if (employeeId == null) {
        redirectAttributes.addFlashAttribute("error", "No employee logged in");
        return "redirect:/employee/returnRequests.htm";
    }

    if (newReturnRequestStatus == null || newReturnRequestStatus.isEmpty()) {
        newReturnRequestStatus = newOrderStatus; // Fallback if newReturnRequestStatus is not set
    }

    System.out.println("Processing orderID: " + orderID + ", employeeID from session: " + employeeId);
    System.out.println("Final New Order Status: " + newOrderStatus + ", Final New Return Request Status: " + newReturnRequestStatus);

    ReturnRequestResponse response = new ReturnRequestResponse();
    response.setOrderID(orderID);
    response.setEmployeeID(employeeId);
    response.setReturnRequestResponseDate(LocalDateTime.now());
    response.setMessage(message);

    returnRequestResponseDAO.addReturnRequestResponse(response);
    returnRequestResponseDAO.updateOrderStatus(orderID, newOrderStatus, newReturnRequestStatus);

    redirectAttributes.addFlashAttribute("success", "Return request processed successfully");
    return "redirect:/employee/returnRequests.htm";
}

    @PostMapping("/deleteCompletedReturn")
    public String deleteCompletedReturnOrder(@RequestParam("orderID") int orderID, RedirectAttributes redirectAttributes) {
        try {
            returnRequestResponseDAO.deleteCompletedReturnOrder(orderID);
            redirectAttributes.addFlashAttribute("success", "Completed return order deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete completed return order: " + e.getMessage());
        }
        return "redirect:/employee/returnRequests";
    }
}
