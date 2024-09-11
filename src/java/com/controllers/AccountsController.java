package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.services.SessionService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AccountsController {

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String showAccounts(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        List<Accounts> listAccount = accountsDAO.findAll();
        listAccount.forEach(account -> account.setRoleName(getRoleName(account.getRole())));

        model.addAttribute("listAccount", listAccount);
        return "/admin/account";
    }

    @RequestMapping(value = "/accountCreate", method = RequestMethod.GET)
    public String showAccountCreateForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "/admin/accountCreate";
    }

    @RequestMapping(value = "/admin/accountCreate.htm", method = RequestMethod.POST)
    public String createAccount(
            @RequestParam("fullName") @NotBlank String fullName,
            @RequestParam("email") @NotBlank String email,
            @RequestParam("password") @NotBlank String password,
            @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
            @RequestParam("phoneNumber") @NotBlank String phoneNumber,
            @RequestParam("address") @NotBlank String address,
            @RequestParam("gender") @NotBlank String gender,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
            @RequestParam("role") int role,
            Model model) {

        Map<String, String> errors = new HashMap<>();

        if (fullName.isEmpty()) {
            errors.put("fullName", "Full name is required.");
        }
        if (email.isEmpty()) {
            errors.put("email", "Email is required.");
        } else if (accountsDAO.existsByEmail(email)) {
            errors.put("email", "Email already exists.");
        }
        if (password.isEmpty() || password.length() < 6 || !password.matches(".*[0-9].*") || !password.matches(".*[a-zA-Z].*")) {
            errors.put("password", "Password must be at least 6 characters long and contain both letters and numbers.");
        }
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            errors.put("confirmPassword", "Confirm password does not match the password.");
        }
        if (phoneNumber.isEmpty()) {
            errors.put("phoneNumber", "Phone number is required.");
        } else if (accountsDAO.existsByPhoneNumber(phoneNumber)) {
            errors.put("phoneNumber", "Phone number already exists.");
        }
        if (address.isEmpty()) {
            errors.put("address", "Address is required.");
        }
        if (gender.isEmpty()) {
            errors.put("gender", "Gender is required.");
        }
        if (birthday == null) {
            errors.put("birthday", "Birthday is required.");
        } else {
            LocalDate today = LocalDate.now();
            int age = Period.between(birthday, today).getYears();
            if (age < 14) {
                errors.put("birthday", "You must be at least 14 years old to register.");
            }
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("account", new Accounts(0, phoneNumber, password, email, role, gender, fullName, java.sql.Date.valueOf(birthday), address, BigDecimal.ZERO));
            return "/admin/accountCreate";
        }

        String hashedPassword = passwordEncoder.encode(password);

        Accounts newAccount = new Accounts(0, phoneNumber, hashedPassword, email, role, gender, fullName, java.sql.Date.valueOf(birthday), address, BigDecimal.ZERO);
        try {
            accountsDAO.save(newAccount);
        } catch (Exception e) {
            model.addAttribute("error", "Account creation failed. Please try again.");
            return "/admin/accountCreate";
        }

        return "redirect:/admin/account.htm";
    }

    @RequestMapping(value = "/accountUpdate.htm", method = RequestMethod.GET)
    public String showEditAccountForm(@RequestParam("accountID") int id, Model model) {
        Accounts account = accountsDAO.findById(id);
        if (account == null) {
            throw new IllegalArgumentException("Invalid account Id: " + id);
        }
        model.addAttribute("account", account);
        return "/admin/accountUpdate";
    }

    @RequestMapping(value = "/editAccount.htm", method = RequestMethod.POST)
    public String updateAccount(@RequestParam("accountID") int id, @ModelAttribute("account") Accounts account, BindingResult result, Model model) {
        Accounts existingAccount = accountsDAO.findById(id);

        if (existingAccount == null) {
            result.rejectValue("accountID", "error.account", "Account not found.");
            return "/admin/accountUpdate";
        }

        if (accountsDAO.existsByEmail(account.getEmail()) && !existingAccount.getEmail().equals(account.getEmail())) {
            result.rejectValue("email", "error.account", "Email already exists.");
        }

        if (!account.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            result.rejectValue("email", "error.account", "Email is not in correct format.");
        }

        if (accountsDAO.existsByPhoneNumber(account.getPhoneNumber()) && !existingAccount.getPhoneNumber().equals(account.getPhoneNumber())) {
            result.rejectValue("phoneNumber", "error.account", "Phone Number already exists.");
        }

        if (result.hasErrors()) {
            model.addAttribute("account", account);
            return "/admin/accountUpdate";
        }

        existingAccount.setEmail(account.getEmail());
        existingAccount.setGender(account.getGender());
        existingAccount.setRole(account.getRole());
        existingAccount.setPhoneNumber(account.getPhoneNumber());

        accountsDAO.update(existingAccount);
        return "redirect:/admin/account.htm";
    }

    @RequestMapping(value = "accountDelete", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam("accountID") int id, Model model) {
        try {
//            // Kiểm tra nếu tài khoản có liên kết với các bảng khác
//            boolean hasDependencies = accountsDAO.hasDependencies(id);
//            
//            if (hasDependencies) {
//                model.addAttribute("errorMessage", "Cannot delete account because it has dependencies in other tables.");
//                return "/admin/account";  // Trả về trang lỗi hoặc thông báo lỗi nếu có liên kết
//            }

            // Xóa tài khoản nếu không có liên kết
            accountsDAO.deleteById(id);
            return "redirect:/admin/account.htm";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while deleting the account: " + e.getMessage());
            return "redirect:/admin/account";  // Trả về trang lỗi nếu có lỗi xảy ra
        }
    }

    @GetMapping("/information")
    public String showAccountInformation(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            model.addAttribute("error", "You must be logged in to view account information.");
            return "redirect:/login.htm";
        }

        Accounts account = accountsDAO.findByEmail(email);
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "redirect:/login.htm";
        }

        model.addAttribute("account", account);
        return "/admin/accountInformation";
    }

    private String getRoleName(int role) {
        switch (role) {
            case 1:
                return "Admin";
            case 2:
                return "Employee";
            case 3:
                return "Shipper";
            case 4:
                return "Customer";
            default:
                return "Unknown";
        }
    }
}
