package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private AccountsDAO accountsDAO;

    // Display employee index page
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showEmployee(HttpServletRequest request, ModelMap model) {
        // Check if the session exists
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm"; // Redirect to login if session is invalid
        }

        // Get the search keyword
        String keyword = request.getParameter("keyword");

        // Get the current page, default to 1 if not provided
        int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

        // Ensure page is not less than 1
        if (page < 1) {
            page = 1;
        }

        // Set page size (number of records per page)
        int pageSize = 10;

        // If keyword is null or empty, set it to an empty string for default search
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = ""; // This will match all employees
        }

        // Get total number of records based on the search keyword
        int totalRecords = accountsDAO.getTotalAccounts(keyword);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // If no accounts found, reset to page 1 and make sure totalPages is 1 to avoid incorrect pagination
        if (totalRecords == 0) {
            page = 1;
            totalPages = 1;
        }

        // Fetch the list of accounts for the current page and search keyword
        List<Accounts> listAccount = accountsDAO.getPagedAccounts(keyword, page, pageSize);

        // Add attributes to the model to be used in the view
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        // Return the view page to display accounts
        return "/employee/index";
    }
}
