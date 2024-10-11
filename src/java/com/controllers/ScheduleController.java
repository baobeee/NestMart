/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.DayOfWeek;
import com.models.DayOfWeekDAO;
import com.models.SalaryDAO;
import com.models.Shift;
import com.models.ShiftDAO;
import com.models.WeekDetails;
import com.models.WeekDetailsDAO;
import com.models.WeekSchedule;
import com.models.WeekScheduleDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private WeekDetailsDAO weekDetailsDAO;

    @Autowired
    private DayOfWeekDAO dayOfWeekDAO;

    @Autowired
    private ShiftDAO shiftDAO;

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private SalaryDAO salaryDAO;

    //display all week schedule
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String getWeekScheduleList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
// Lấy tham số trang, mặc định là 1 nếu không có tham số trang
    int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

    // Đảm bảo page không nhỏ hơn 1
    if (page < 1) {
        page = 1;
    }

    // Xác định số lượng bản ghi mỗi trang
    int pageSize = 10; // Số lượng bản ghi mỗi trang

    // Tính tổng số bản ghi
    int totalRecords = weekScheduleDAO.getTotalWeekSchedules();
    int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

    // Nếu không có lịch nào, thiết lập trang về 1 và tổng số trang là 1
    if (totalRecords == 0) {
        page = 1;
        totalPages = 1; // Để đảm bảo không có phân trang khi không có lịch
    }

    // Lấy danh sách lịch tuần với phân trang
    List<WeekSchedule> weekSchedules = weekScheduleDAO.getPagedWeekSchedules(page, pageSize);

    // Thêm vào model
    model.addAttribute("weekSchedules", weekSchedules);
    model.addAttribute("totalPages", totalPages);
    model.addAttribute("currentPage", page);
        return "admin/schedule";
    }

    @RequestMapping(value = "/scheduleDetails.htm", method = RequestMethod.GET)
    public String getWeekScheduleDetails(@RequestParam("weekScheduleID") int weekScheduleID,
            Model model,
            HttpServletRequest request) {
        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Fetch week schedule, week details, days of the week, shifts, and accounts
        WeekSchedule weekSchedule = weekScheduleDAO.findByID(weekScheduleID);
        List<WeekDetails> weekDetailsList = weekDetailsDAO.findByWeekScheduleID(weekScheduleID);
        List<DayOfWeek> dayOfWeekList = dayOfWeekDAO.findAll();
        List<Shift> shiftList = shiftDAO.findAll();
        List<Accounts> accountsList = accountsDAO.findAll(); // Get employee list

        // Filter accounts to include only employees (role 2) and shippers (role 3)
        List<Accounts> filteredAccountsList = accountsList.stream()
                .filter(account -> {
                    Integer role = account.getRole();
                    return role != null && (role == 2 || role == 3); // 2 is Employee, 3 is Shipper
                })
                .collect(Collectors.toList());

        // Create map to look up employee names by employeeID
        Map<Integer, String> employeeMap = filteredAccountsList.stream()
                .collect(Collectors.toMap(Accounts::getAccountID, Accounts::getFullName));

        // Create map to look up roles by employeeID
        Map<Integer, String> roleMap = filteredAccountsList.stream()
                .collect(Collectors.toMap(
                        Accounts::getAccountID,
                        account -> {
                            Integer role = account.getRole(); // Check if role exists
                            if (role != null) {
                                switch (role) {
                                    case 2:
                                        return "Employee";
                                    case 3:
                                        return "Shipper";
                                    default:
                                        return "Unknown Role";
                                }
                            }
                            return "Unknown Role";
                        }
                ));

        // map shift
        Map<Integer, Shift> shiftMap = shiftList.stream()
                .collect(Collectors.toMap(Shift::getShiftID, shift -> shift));

        // Group week details by day and shift
        Map<String, Map<String, List<WeekDetails>>> scheduleMap = new LinkedHashMap<>();
        if (weekDetailsList != null && !weekDetailsList.isEmpty()) {
            Map<Integer, String> dayNameMap = dayOfWeekList.stream()
                    .collect(Collectors.toMap(DayOfWeek::getDayID, DayOfWeek::getDayName));
            Map<Integer, String> shiftNameMap = shiftList.stream()
                    .collect(Collectors.toMap(Shift::getShiftID, Shift::getShiftName));

            // Group details by day and shift
            scheduleMap = weekDetailsList.stream()
                    .collect(Collectors.groupingBy(
                            wd -> dayNameMap.getOrDefault(wd.getDayID(), "Unknown Day"),
                            Collectors.groupingBy(wd -> shiftNameMap.getOrDefault(wd.getShiftID(), "Unknown Shift"))
                    ));
        }

        // Sort schedule by order from Monday to Sunday
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        // Sort the keys into sortedScheduleMap
        Map<String, Map<String, List<WeekDetails>>> sortedScheduleMap = new LinkedHashMap<>();
        for (String day : daysOfWeek) {
            if (scheduleMap.containsKey(day)) {
                sortedScheduleMap.put(day, scheduleMap.get(day));
            }
        }

        // Add the necessary information to the model
        model.addAttribute("weekSchedule", weekSchedule);
        model.addAttribute("weekDetailsGroupedByDayAndShift", sortedScheduleMap); // Use the sorted map
        model.addAttribute("employeeMap", employeeMap); // Send employee name map
        model.addAttribute("roleMap", roleMap); // Send role map
        model.addAttribute("shiftMap", shiftMap); // Send shift map
        model.addAttribute("dayOfWeekList", dayOfWeekList); // List of days for the view
        model.addAttribute("shiftList", shiftList); // List of shifts for the view
        model.addAttribute("accountsList", filteredAccountsList); // Use filtered accounts list
        model.addAttribute("hasData", !weekDetailsList.isEmpty());

        return "admin/scheduleDetails";
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
            return "admin/schedule";
        }

        // Kiểm tra xem tuần có đủ 7 ngày không
        long daysBetween = ChronoUnit.DAYS.between(weekStartDate, weekEndDate) + 1;
        if (daysBetween != 7) {
            model.addAttribute("error", "A work week must have 7 days.");
            return "admin/schedule";
        }

        // Kiểm tra xem lịch tuần đã tồn tại chưa
        if (weekScheduleDAO.isWeekScheduleExists(Date.valueOf(weekStartDate), Date.valueOf(weekEndDate))) {
            model.addAttribute("error", "The work schedule has existed for this period of time.");
            return "admin/schedule";
        }

        // Tạo tuần lịch mới
        WeekSchedule weekSchedule = new WeekSchedule();
        weekSchedule.setWeekStartDate(Date.valueOf(weekStartDate));
        weekSchedule.setWeekEndDate(Date.valueOf(weekEndDate));
        weekScheduleDAO.save(weekSchedule);

        // Chuyển hướng về trang schedule sau khi tạo thành công
        return "redirect:/admin/schedule.htm";
    }

    //delete weekSchedule
    @RequestMapping(value = "/weekScheduleDelete", method = RequestMethod.GET)
    public String deleteWeekSchedule(@RequestParam("weekScheduleID") int weekScheduleID,
            RedirectAttributes redirectAttributes) {
        // Kiểm tra xem tuần làm việc có tồn tại không
        WeekSchedule weekSchedule = weekScheduleDAO.findByID(weekScheduleID);
        if (weekSchedule == null) {
            redirectAttributes.addFlashAttribute("error", "Week schedule not found.");
            return "redirect:/admin/schedule.htm"; // Hoặc trang khác bạn muốn chuyển hướng
        }

        // Kiểm tra xem tuần làm việc đã có phân công chưa
        List<WeekDetails> assignments = weekDetailsDAO.findByWeekScheduleID(weekScheduleID);
        if (assignments != null && !assignments.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete this schedule as it has assignments.");
            return "redirect:/admin/schedule.htm";
        }

        weekScheduleDAO.delete(weekScheduleID);
        redirectAttributes.addFlashAttribute("success", "Week schedule deleted successfully.");
        return "redirect:/admin/schedule.htm";
    }

    //assign shift
    @RequestMapping(value = "/assignShift", method = RequestMethod.POST)
    public String assignShift(@RequestParam("weekScheduleID") int weekScheduleID,
            @RequestParam("employeeID") int employeeID,
            @RequestParam("dayID") int dayID,
            @RequestParam("shiftID") int shiftID,
            RedirectAttributes redirectAttributes) {

        // Kiểm  WeekSchedule có có exits k
        WeekSchedule weekSchedule = weekScheduleDAO.findByID(weekScheduleID);
        if (weekSchedule == null) {
            redirectAttributes.addFlashAttribute("error", "Week schedule not found.");
            return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekScheduleID;
        }

        // Kiểm emp exist
        Accounts account = accountsDAO.findById(employeeID);
        if (account == null) {
            redirectAttributes.addFlashAttribute("error", "Employee not found.");
            return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekScheduleID;
        }

        // Kiểm ca làm và ngày làm 
        DayOfWeek day = dayOfWeekDAO.findById(dayID);
        Shift shift = shiftDAO.findById(shiftID);
        if (day == null || shift == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid day or shift.");
            return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekScheduleID;
        }

        // Kiểm WeekDetails đã tồn tại chưa để quyết định thêm mới hay cập nhật
        WeekDetails existingWeekDetails = weekDetailsDAO.findByScheduleAndDayAndShift(weekScheduleID, dayID, shiftID, employeeID);
        WeekDetails weekDetails;

        if (existingWeekDetails == null) {
            // Nếu chưa tồn tại thì tạo mới
            weekDetails = new WeekDetails();
            weekDetails.setWeekScheduleID(weekScheduleID);
            weekDetails.setEmployeeID(employeeID);
            weekDetails.setDayID(dayID);
            weekDetails.setShiftID(shiftID);
        } else {
            weekDetails = existingWeekDetails;
        }

        weekDetailsDAO.save(weekDetails);

        redirectAttributes.addFlashAttribute("success", "Shift assigned and salary updated successfully.");
        return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekScheduleID;
    }

    @RequestMapping(value = "/editWeekDetail.htm", method = RequestMethod.GET)
    public String showEditWeekDetailForm(@RequestParam("weekDetailID") int weekDetailID,
            Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // tìm weekSchedule để chỉnh sửa
        WeekDetails weekDetail = weekDetailsDAO.findByID(weekDetailID);
        if (weekDetail == null) {
            model.addAttribute("error", "Week detail not found.");
            return "redirect:/admin/schedule.htm";
        }

        // Lấy list
        List<Accounts> accountsList = accountsDAO.findAll();
        List<DayOfWeek> dayOfWeekList = dayOfWeekDAO.findAll();
        List<Shift> shiftList = shiftDAO.findAll();

        // Thêm vào model
        model.addAttribute("weekDetail", weekDetail);
        model.addAttribute("accountsList", accountsList);
        model.addAttribute("dayOfWeekList", dayOfWeekList);
        model.addAttribute("shiftList", shiftList);

        return "admin/scheduleDetailsUpdate";
    }

    @RequestMapping(value = "/editWeekDetail", method = RequestMethod.POST)
    public String editWeekDetail(@ModelAttribute("weekDetail") WeekDetails weekDetail,
            RedirectAttributes redirectAttributes,
            Model model) {


        if (weekDetail.getEmployeeID() == 0) {
            redirectAttributes.addFlashAttribute("error", "Please select an employee.");
            return "redirect:/admin/editWeekDetail.htm?weekDetailID=" + weekDetail.getWeekDetailID();
        }
        if (weekDetail.getDayID() == 0) {
            redirectAttributes.addFlashAttribute("error", "Please select a day.");
            return "redirect:/admin/editWeekDetail.htm?weekDetailID=" + weekDetail.getWeekDetailID();
        }
        if (weekDetail.getShiftID() == 0) {
            redirectAttributes.addFlashAttribute("error", "Please select a shift.");
            return "redirect:/admin/editWeekDetail.htm?weekDetailID=" + weekDetail.getWeekDetailID();
        }

        // Filter employees with roles 2 and 3
        List<Integer> roles = Arrays.asList(2, 3);
        List<Accounts> filteredAccounts = accountsDAO.findEmployeesByRoles(roles);
        model.addAttribute("filteredAccounts", filteredAccounts);

        // Check if the week detail exists
        WeekDetails existingDetail = weekDetailsDAO.findByID(weekDetail.getWeekDetailID());
        if (existingDetail == null) {
            redirectAttributes.addFlashAttribute("error", "Week detail not found.");
            return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekDetail.getWeekScheduleID();
        }

        // Check for overlapping shifts
        List<WeekDetails> overlappingDetails = weekDetailsDAO.findByShiftAndDay(weekDetail.getShiftID(), weekDetail.getDayID());
        for (WeekDetails detail : overlappingDetails) {
            if (detail.getEmployeeID() != existingDetail.getEmployeeID() && detail.getEmployeeID() == weekDetail.getEmployeeID()) {
                redirectAttributes.addFlashAttribute("error", "This shift is already taken by another employee.");
                return "redirect:/admin/editWeekDetail.htm?weekDetailID=" + weekDetail.getWeekDetailID();
            }
        }

        // Update weekDetails with new data including overtimeHours
        existingDetail.setEmployeeID(weekDetail.getEmployeeID());
        existingDetail.setDayID(weekDetail.getDayID());
        existingDetail.setShiftID(weekDetail.getShiftID());
        existingDetail.setOvertimeHours(weekDetail.getOvertimeHours());

        weekDetailsDAO.update(existingDetail);

        redirectAttributes.addFlashAttribute("message", "Week detail and salary updated successfully.");
        return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekDetail.getWeekScheduleID();
    }

    //delete
    @RequestMapping(value = "/deleteShift", method = RequestMethod.POST)
    public String deleteShift(@RequestParam(value = "shiftID") int shiftID,
            @RequestParam(value = "weekScheduleID") int weekScheduleID,
            @RequestParam(value = "weekDetailID") int weekDetailID,
            RedirectAttributes redirectAttributes) {

        Shift existingShift = shiftDAO.findById(shiftID);
        if (existingShift == null) {
            redirectAttributes.addFlashAttribute("error", "Shift not found.");
        } else {
            try {
                weekDetailsDAO.delete(weekDetailID);

                shiftDAO.delete(shiftID);

                redirectAttributes.addFlashAttribute("message", "Shift and associated details deleted successfully.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Failed to delete shift: " + e.getMessage());
            }
        }

        return "redirect:/admin/scheduleDetails.htm?weekScheduleID=" + weekScheduleID;
    }

}
