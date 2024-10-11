package com.controllers;

import com.models.Accounts;
import com.models.Notifications;
import com.models.NotificationsDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/employee")
public class NotificationsEmployeeController {

    @Autowired
    NotificationsDAO notificationsDAO;

    @RequestMapping(value = "viewNotifications", method = RequestMethod.GET)
    public String showNotifications(@RequestParam(value = "status", required = false) String status,
            ModelMap model) {
        int employeeID = 2;
        List<Notifications> notifications;
        if (status != null && !status.isEmpty()) {
            notifications = notificationsDAO.findNotificationsByEmployeeIdAndStatus(employeeID, status);
        } else {
            notifications = notificationsDAO.findNotificationsByEmployeeId(employeeID);
        }

        model.addAttribute("notifications", notifications);
        return "/employee/notificationsView";
    }

    @RequestMapping(value = "empDeleteNotification", method = RequestMethod.GET)
    public String empDeleteNotification(@RequestParam("notificationID") int notificationID) {
        notificationsDAO.delete(notificationID);
        return "redirect:viewNotifications.htm";
    }

    @RequestMapping(value = "findNotifications", method = RequestMethod.GET)
    public String findNotification(String keyword, ModelMap model) {
        List<Notifications> notificationsList = notificationsDAO.findNotifications(keyword);
        model.addAttribute("notificationsList", notificationsList);
        return "/employee/notificationsView";
    }

    @ModelAttribute("addNotificationForm")
    public Notifications notificationForm() {
        return new Notifications();
    }

    @RequestMapping(value = "addNotificationForm", method = RequestMethod.GET)
    public String showInventoryForm(ModelMap model) {
        List<Accounts> accounts = notificationsDAO.getAllCustomer();
        model.addAttribute("accountsList", accounts);
        return "/employee/notificationsCreate";
    }

    @RequestMapping(value = "addNotification", method = RequestMethod.POST)
    public String createNotification(@ModelAttribute("notificationsCreate") @Valid Notifications noti, BindingResult br, HttpSession session) {
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        if (br.hasErrors()) {
            return "/employee/notificationsCreate";
        }

        if (noti.getSendDate() == null) {
            LocalDate localDate = LocalDate.now();
            Date sqlDate = Date.valueOf(localDate);
            noti.setSendDate(sqlDate);
        }

        Integer employeeID = (Integer) session.getAttribute("accountId");
        noti.setEmployeeID(employeeID);

        notificationsDAO.add(noti);

        return "redirect:viewNotifications.htm";
    }

    @ModelAttribute("editNotificationForm")
    public Notifications editInventoryForm() {
        return new Notifications();
    }

    @RequestMapping(value = "updateNotification", method = RequestMethod.GET)
    public String updateNotificationForm(@RequestParam("notificationID") int notificationID, ModelMap model) {
        Notifications noti = notificationsDAO.get(notificationID);
        model.addAttribute("notification", noti);
        return "/employee/notificationsUpdate";
    }

    @RequestMapping(value = "editNotification", method = RequestMethod.POST)
    public String updateNotification(@ModelAttribute("editNotificationForm") @Valid Notifications noti, BindingResult br) {
        if (br.hasErrors()) {
            return "/employee/notificationsUpdate";
        }

        if (noti.getSendDate() == null) {
            LocalDate localDate = LocalDate.now();
            Date sqlDate = Date.valueOf(localDate);
            noti.setSendDate(sqlDate);
        }

        notificationsDAO.update(noti);

        return "redirect:viewNotifications.htm";
    }

    @RequestMapping(value = "empFindNotifications", method = RequestMethod.GET)
    public String findNotification(String keyword, ModelMap model, HttpSession session) {
        Integer employeeID = (Integer) session.getAttribute("accountId");
        List<Notifications> notifications = notificationsDAO.findNotificationsByEmployeeID(employeeID, keyword);
        model.addAttribute("notifications", notifications);
        return "/employee/notificationsView";
    }
}
