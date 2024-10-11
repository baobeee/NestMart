package com.controllers;

import com.models.*;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SalaryController {

    private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

    @Autowired
    private SalaryDAO salaryDAO;

    @Autowired
    private WeekScheduleDAO weekScheduleDAO;

    @Autowired
    private WeekDetailsDAO weekDetailsDAO;

    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public String salaryList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        try {
            List<WeekSalaryDTO> weekSalaryList = salaryDAO.calculateWeeklySalaries();
            if (weekSalaryList == null) {
                weekSalaryList = new ArrayList<>();
            }
            model.addAttribute("weekSalaryList", weekSalaryList);
        } catch (Exception e) {
            logger.error("Error occurred while fetching weekly salaries", e);
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi lấy dữ liệu tuần lương.");
        }

        return "/admin/salary";
    }

    @RequestMapping(value = "/salaryDetails", method = RequestMethod.GET)
public String salaryDetails(@RequestParam int weekScheduleID, Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("email") == null) {
        return "redirect:/login.htm";
    }
    
    try {
        if (salaryDAO == null) {
            throw new IllegalStateException("salaryDAO is not initialized");
        }
        if (weekScheduleDAO == null) {
            throw new IllegalStateException("weekScheduleDAO is not initialized");
        }

        List<EmployeeSalaryDTO> salaryDetails = salaryDAO.findSalariesWithHoursByWeek(weekScheduleID);
        if (salaryDetails == null) {
            salaryDetails = new ArrayList<>();
        }
        model.addAttribute("salaryDetails", salaryDetails);

        // Calculate total salary
        BigDecimal totalSalary = salaryDetails.stream()
                .filter(s -> s.getTotalSalary() != null)
                .map(EmployeeSalaryDTO::getTotalSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalSalary", totalSalary);

        // Calculate total hours
        int totalHoursWorked = salaryDetails.stream()
                .mapToInt(EmployeeSalaryDTO::getTotalHours)
                .sum();
        model.addAttribute("totalHours", totalHoursWorked);

        // Fetch week information
        WeekSchedule weekSchedule = weekScheduleDAO.findByID(weekScheduleID);
        if (weekSchedule == null) {
            throw new IllegalArgumentException("Cannot find week information for weekScheduleID: " + weekScheduleID);
        }
        
        System.out.println("Week Schedule: " + weekSchedule);
        model.addAttribute("weekSchedule", weekSchedule);

    } catch (Exception e) {
        logger.error("Error occurred while fetching salary details", e);
        model.addAttribute("errorMessage", "Có lỗi xảy ra khi lấy dữ liệu lương: " + e.getMessage());
        return "/error"; // Redirect to an error page
    }

    return "/admin/salaryDetails";
}

}
