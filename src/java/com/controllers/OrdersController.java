package com.controllers;

import com.models.OrderDetails;
import com.models.Orders;
import com.models.OrdersDAO;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
public class OrdersController {
    @Autowired
    OrdersDAO ordersDAO;

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    public String viewOrders(ModelMap model, @RequestParam(required = false) String status, HttpSession session) {
        Integer customerID = (Integer) session.getAttribute("accountId");
        
//       
        if (customerID == null) {
            return "redirect:/login.htm"; 
        }
        List<Orders> ordersList;
        if (status == null || status.isEmpty()) {
            ordersList = ordersDAO.findAllOrders(customerID);
        } else {
            ordersList = ordersDAO.getOrdersByStatus(status, customerID);
        }
        
        for (Orders order : ordersList) {
            if ("Return Requested".equals(order.getOrderStatus()) ||
                "Approved".equals(order.getOrderStatus()) ||
                "Denied".equals(order.getOrderStatus()) ||
                "Returning".equals(order.getOrderStatus()) ||
                "Returned".equals(order.getOrderStatus())) {
                
             
                Orders fullOrder = ordersDAO.get(String.valueOf(order.getOrderID()));
                order.setReturnRequestStatus(fullOrder.getReturnRequestStatus());
                order.setReturnRequestReason(fullOrder.getReturnRequestReason());
                order.setReturnRequestDate(fullOrder.getReturnRequestDate());
            }
            
            for (OrderDetails orderDetail : order.getOrderDetails()) {
                Integer feedbackID = ordersDAO.getFeedback(orderDetail.getProduct().getProductID(), customerID);
                boolean hasFeedback = feedbackID != null;
                orderDetail.setHasFeedback(hasFeedback);
                orderDetail.setFeedbackID(feedbackID);
                System.out.println("ProductID: " + orderDetail.getProduct().getProductID() 
                + " Has Feedback: " + hasFeedback + " FeedbackID: " + feedbackID);
            }
        }
        model.addAttribute("ordersList", ordersList);
        return "/client/orderHistory";
    }
    
    @RequestMapping(value = "returnOrder", method = RequestMethod.POST)
    public String returnOrder(@RequestParam("orderId") int orderId,
            @RequestParam("reason") String reason,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        Integer employeeId = (Integer) session.getAttribute("accountId");
        if (employeeId == null) {
            return "redirect:/login.htm";
        }

        Orders order = ordersDAO.get(String.valueOf(orderId));
        if (order != null && "Completed".equals(order.getOrderStatus())) {
            order.setOrderStatus("Return Requested");
            order.setReturnRequestDate(LocalDateTime.now());
            order.setReturnRequestStatus("Pending");
            order.setReturnRequestReason(reason);

            try {
                ordersDAO.update(order);
                redirectAttributes.addFlashAttribute("message", "Return request submitted successfully");
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to submit return request due to a database error.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to submit return request. Order not found or not eligible for return.");
        }
        return "redirect:/client/orderHistory.htm";
    }

    @RequestMapping(value = "updateReturnStatus", method = RequestMethod.POST)
    public String updateReturnStatus(@RequestParam("orderId") int orderId,
            @RequestParam("status") String status,
            @RequestParam("employeeId") int employeeId,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {
        Orders order = ordersDAO.get(String.valueOf(orderId));
        if (order != null && "Return Requested".equals(order.getOrderStatus())) {
            // Cập nhật bảng Orders
            order.setReturnRequestStatus(status);
            if ("On Delivery".equals(status)) {
                order.setOrderStatus("Returning");
            } else if ("Completed".equals(status)) {
                order.setOrderStatus("Returned");
            }
            ordersDAO.update(order);

            redirectAttributes.addFlashAttribute("message", "Return request status updated successfully and response recorded");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update return request status. Order not found or not eligible for update.");
        }
        return "redirect:/client/orderHistory.htm";
    }
    @RequestMapping(value = "updateOrder", method = RequestMethod.GET)
    public String updateCancelled(@RequestParam("orderID") int orderID,
            HttpSession session) {
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        try {
            ordersDAO.updateCancalledStatus(orderID);
            return "redirect:/client/orderHistory.htm";
        } catch (Exception e) {
            return "error";
        }
    }
}

