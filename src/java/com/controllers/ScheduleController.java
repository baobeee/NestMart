/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.DayOfWeek;
import com.models.DayOfWeekDAO;
import com.models.Shift;
import com.models.ShiftDAO;
import com.models.WeekDetails;
import com.models.WeekDetailsDAO;
import com.models.WeekSchedule;
import com.models.WeekScheduleDAO;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Win10
 */
@Controller
@RequestMapping("/admin")
public class ScheduleController {

    @Autowired
    private WeekScheduleDAO weekScheduleDAO;

    // Hiển thị danh sách các lịch làm việc
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String viewWeekSchedules(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        List<WeekSchedule> weekSchedules = weekScheduleDAO.getAllWeekSchedules();
        model.addAttribute("weekSchedules", weekSchedules);
        return "/admin/schedule";
    }

    //create/delete WeekSchedule
    @RequestMapping(value = "/scheduleCreate", method = RequestMethod.GET)
    public String showCreateWeekScheduleForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        return "/admin/scheduleCreate";
    }

    @RequestMapping(value = "/weekScheduleCreate", method = RequestMethod.POST)
    public String createWeekSchedule(
            @RequestParam("weekStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate weekStartDate,
            @RequestParam("weekEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate weekEndDate,
            HttpServletRequest request,
            Model model) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Kiểm tra xem weekStartDate không sau weekEndDate
        if (weekStartDate.isAfter(weekEndDate)) {
            model.addAttribute("error", "The start date cannot be after the end date.");
            return "/admin/scheduleCreate";
        }

        // Kiểm tra xem tuần có đủ 7 ngày k
        long daysBetween = ChronoUnit.DAYS.between(weekStartDate, weekEndDate) + 1;
        if (daysBetween != 7) {
            model.addAttribute("error", "A work week must have 7 days.");
            return "/admin/scheduleCreate";
        }

        if (weekScheduleDAO.isWeekScheduleExists(Date.valueOf(weekStartDate), Date.valueOf(weekEndDate))) {
            model.addAttribute("error", "The work schedule has existed for this period of time.");
            return "/admin/scheduleCreate"; // Trả lại trang nếu có lỗi
        }

        WeekSchedule weekSchedule = new WeekSchedule();
        weekSchedule.setWeekStartDate(Date.valueOf(weekStartDate));
        weekSchedule.setWeekEndDate(Date.valueOf(weekEndDate));

        weekScheduleDAO.createWeekSchedule(weekSchedule);

        return "redirect:/admin/schedule.htm";
    }

    @RequestMapping(value = "/weekScheduleDelete", method = RequestMethod.GET)
    public String deleteWeekSchedule(
            @RequestParam("weekScheduleID") int weekScheduleID,
            Model model) {

        // Lấy đối tượng WeekSchedule từ CSDL dựa vào ID
        WeekSchedule weekSchedule = weekScheduleDAO.getWeekScheduleById(weekScheduleID);

        // Kiểm tra nếu weekSchedule null, tránh lỗi NullPointerException
        if (weekSchedule == null) {
            model.addAttribute("error", "Lịch tuần không tồn tại.");
            return "redirect:/admin/schedule.htm";
        }

        // Kiểm tra nếu lịch tuần có dữ liệu liên quan trong bảng chi tiết (ví dụ: day name, shift...)
        boolean hasDetails = weekScheduleDAO.hasDetailsForWeek(weekScheduleID);

        // Nếu lịch tuần có dữ liệu chi tiết, không cho phép xoá
        if (hasDetails) {
            model.addAttribute("error", "Lịch tuần không thể xóa vì đã có chi tiết ca làm việc. Vui lòng xoá chi tiết trước.");
            return "admin/schedule"; // Trả lại trang nếu có lỗi
        } else {
            // Thực hiện xóa nếu không có dữ liệu chi tiết liên quan
            weekScheduleDAO.deleteWeekSchedule(weekScheduleID);
            model.addAttribute("success", "Lịch tuần đã được xóa thành công.");
            return "redirect:/admin/schedule.htm"; // Điều hướng đến trang danh sách lịch
        }
    }

    //WeekDetails
    @RequestMapping(value = "/scheduleDetails", method = RequestMethod.GET)
    public String viewWeekScheduleDetails(@RequestParam("weekScheduleID") int weekScheduleID, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        // Lấy thông tin lịch
        LinkedHashMap<String, Map<String, List<WeekDetails>>> weekDetailsGroupedByDayAndShift
                = weekScheduleDAO.getWeekDetailsGroupedByDay(weekScheduleID);
        model.addAttribute("weekDetailsGroupedByDayAndShift", weekDetailsGroupedByDayAndShift);

        // Lấy thông tin tuần
        WeekSchedule weekSchedule = weekScheduleDAO.getWeekScheduleById(weekScheduleID);
        model.addAttribute("weekSchedule", weekSchedule);

        // Kiểm tra nếu có dữ liệu cho bất kỳ ngày nào trong tuần
        boolean hasData = weekDetailsGroupedByDayAndShift.values().stream().anyMatch(shifts -> shifts.values().stream().anyMatch(list -> !list.isEmpty()));
        model.addAttribute("hasData", hasData);

        // Lấy danh sách các ngày trong tuần
        List<DayOfWeek> daysOfWeek = weekScheduleDAO.getDaysOfWeek();
        model.addAttribute("daysOfWeek", daysOfWeek);

        // Lấy danh sách các ca làm việc
        List<Shift> shifts = weekScheduleDAO.getAllShifts();
        model.addAttribute("shifts", shifts);

        // Lấy danh sách nhân viên với vai trò
        List<Accounts> employees = weekScheduleDAO.getAllEmployees();
        for (Accounts employee : employees) {
            String roleName = getRoleName(employee.getRole());
            employee.setRoleName(roleName);
        }
        model.addAttribute("employees", employees);

        return "/admin/scheduleDetails";
    }

    @RequestMapping(value = "assignShift", method = RequestMethod.GET)
    public String showAssignShiftPage(
            HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        List<WeekSchedule> weekSchedules = weekScheduleDAO.getAllWeekSchedules();
        model.addAttribute("weekSchedules", weekSchedules);

        // Get days and shifts
        List<DayOfWeek> daysOfWeek = weekScheduleDAO.getDaysOfWeek();
        List<Shift> shifts = weekScheduleDAO.getAllShifts();
        List<Accounts> employees = weekScheduleDAO.getAllEmployees();

        model.addAttribute("daysOfWeek", daysOfWeek);
        model.addAttribute("shifts", shifts);
        model.addAttribute("employees", employees);

        

        return "/admin/assignShift";
    }

    @RequestMapping(value = "/assignShift", method = RequestMethod.POST)
public String assignShift(
        @RequestParam("weekScheduleID") int weekScheduleID,
        @RequestParam("dayID") int dayID,
        @RequestParam("shiftID") int shiftID,
        @RequestParam("employeeID") int employeeID, // Chỉ nhận một giá trị duy nhất
        @RequestParam("overtimeHours") BigDecimal overtimeHours,
        @RequestParam("status") String status,
        @RequestParam("notes") String notes,
        HttpServletRequest request, Model model) {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("email") == null) {
        return "redirect:/login.htm";
    }

    // Kiểm tra tính hợp lệ của weekScheduleID
    if (!weekScheduleDAO.isWeekScheduleExists(weekScheduleID)) {
        model.addAttribute("errorMessage", "Week Schedule ID does not exist.");
        return "/admin/error";
    }

    // Insert thông tin ca làm cho một nhân viên duy nhất
    weekScheduleDAO.insertWeekDetails(weekScheduleID, dayID, shiftID, employeeID, overtimeHours, status, notes);

    return "redirect:/schedule"; // Chuyển hướng tới trang lịch biểu
}


    // Phương thức để chuyển đổi mã vai trò thành tên vai trò
    private String getRoleName(int roleCode) {
        switch (roleCode) {
            case 2:
                return "Employee";
            case 3:
                return "Shipper";
            default:
                return "Unknown";
        }
    }

}
