package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.models.Salary;
import com.models.SalaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/admin")
public class SalariesController {

    @Autowired
    private SalaryDAO salaryDAO;

    @Autowired
    private AccountsDAO accountsDAO;

    // Hiển thị danh sách tất cả các bản ghi lương
    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public String listSalaries(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        List<Salary> salaries = salaryDAO.getAllSalaries();
        model.addAttribute("salaries", salaries);
        return "admin/salary";
    }

    @RequestMapping(value = "/salaryDetail", method = RequestMethod.GET)
    public String getSalariesWithDetails(@RequestParam("accountID") int accountID,
            HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        
        List<Salary> salaryDetails = salaryDAO.getSalariesByAccountId(accountID);
        model.addAttribute("salaryDetails", salaryDetails);
        return "admin/salaryDetails";
    }

    // Hiển thị form để tạo một bản ghi lương mới
    @RequestMapping(value = "/salaryCreate", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        List<Accounts> accountsList = accountsDAO.findAll();
        model.addAttribute("accountsList", accountsList);
        model.addAttribute("salary", new Salary());
        return "admin/salaryCreate";
    }

    // Xử lý tạo mới một bản ghi lương
    @RequestMapping(value = "/salaryCreate", method = RequestMethod.POST)
    public String createSalary(Salary salary) {
        salaryDAO.save(salary);
        return "redirect:/admin/salary";
    }

    // Hiển thị form để chỉnh sửa thông tin một bản ghi lương
    @RequestMapping(value = "/salaryEdit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Salary salary = salaryDAO.findById(id);
        List<Accounts> accountsList = accountsDAO.findAll();
        model.addAttribute("salary", salary);
        model.addAttribute("accountsList", accountsList);
        return "admin/salaryEdit";
    }

    // Xử lý cập nhật thông tin một bản ghi lương
    @RequestMapping(value = "/salaryEdit", method = RequestMethod.POST)
    public String updateSalary(Salary salary) {
        salaryDAO.update(salary);
        return "redirect:/admin/salary";
    }

    // Xóa một bản ghi lương dựa trên ID
    @RequestMapping(value = "/salaryDelete/{id}", method = RequestMethod.POST)
    public String deleteSalary(@PathVariable("id") int id) {
        salaryDAO.delete(id);
        return "redirect:/admin/salary";
    }
}
