package com.controllers;

import com.models.Notifications;
import com.models.NotificationsDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class NotificationsController {
    @Autowired
    NotificationsDAO notificationsDAO;
    
    @RequestMapping(value = "notifications", method = RequestMethod.GET)
    public String showNotification(ModelMap model) {
        List<Notifications> notificationsList = notificationsDAO.findAllNotifications();
        model.addAttribute("notificationsList", notificationsList);
        return "/admin/notifications";
    }
    
    
    
    @RequestMapping(value = "deleteNotification", method = RequestMethod.GET)
    public String deleteNotification(@RequestParam("notificationID") int notificationID) {
        notificationsDAO.delete(notificationID);
        return "redirect:notifications.htm";
    }
    
    @RequestMapping(value = "findNotifications", method = RequestMethod.GET)
    public String findNotification(String keyword, ModelMap model){
        List<Notifications> notificationsList = notificationsDAO.findNotifications(keyword);
        model.addAttribute("notificationsList", notificationsList);
        return "/admin/notifications";
    }
}
