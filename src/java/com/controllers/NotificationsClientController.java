package com.controllers;

import com.models.Notifications;
import com.models.NotificationsDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class NotificationsClientController {
    @Autowired
    NotificationsDAO notificationsDAO;
    
    @RequestMapping(value = "viewNotifications", method = RequestMethod.GET)
    public String showNotifications(@RequestParam(value = "status", required = false) String status, 
                                    ModelMap model) {
        int customerID = 4;
        List<Notifications> notifications;
        if (status != null && !status.isEmpty()) {
            notifications = notificationsDAO.findNotificationsByCustomerIdAndStatus(customerID, status);
        } else {
            notifications = notificationsDAO.findNotificationsByCustomerId(customerID);
        }
//        if (customerID == null) {
//            return "redirect:/login";
//        }
        model.addAttribute("notifications", notifications);
        return "/client/notifications";
    }
    
    @RequestMapping(value = "viewNotificationDetails", method = RequestMethod.GET)
    public String viewNotificationDetail(@RequestParam("notificationID") int notificationID, 
                                     ModelMap model) {
        Notifications notification = notificationsDAO.get(notificationID);
        model.addAttribute("notification", notification);

        if ("Unread".equals(notification.getStatus())) {
            notification.setStatus("Read");
            notificationsDAO.update(notification);
        }

        return "/client/notificationDetails"; 
    }
}
