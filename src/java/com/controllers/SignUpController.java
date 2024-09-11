/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Win10
 */
@Controller
@RequestMapping("/")
public class SignUpController {

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String ShowRegisterForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "signup";
    }

    @RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
    public String createAccount(@RequestParam("fullName") @NotBlank String fullName,
            @RequestParam("email") @NotBlank String email,
            @RequestParam("password") @NotBlank String password,
            @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
            @RequestParam("phoneNumber") @NotBlank String phoneNumber,
            @RequestParam("address") @NotBlank String address,
            @RequestParam("gender") @NotBlank String gender,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
            Model model) {

        // Initialization map to store error
        Map<String, String> errors = new HashMap<>();

        // Kiểm tra tên đầy đủ
        if (fullName.isEmpty()) {
            errors.put("fullName", "Full name is required.");
        }

        // Kiểm tra email
        if (email.isEmpty()) {
            errors.put("email", "Email is required.");
        } else if (accountsDAO.existsByEmail(email)) {
            errors.put("email", "Email already exists.");
        }

        // Kiểm tra mật khẩu
        if (password.isEmpty() || password.length() < 6 || !password.matches(".*[0-9].*") || !password.matches(".*[a-zA-Z].*")) {
            errors.put("password", "Password must be at least 6 characters long and contain both letters and numbers.");
        }

        // Kiểm tra xác nhận mật khẩu
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            errors.put("confirmPassword", "Confirm password does not match the password.");
        }

        // Kiểm tra số điện thoại
        if (phoneNumber.isEmpty()) {
            errors.put("phoneNumber", "Phone number is required.");
        } else if (accountsDAO.existsByPhoneNumber(phoneNumber)) {
            errors.put("phoneNumber", "Phone number already exists.");
        }

        // Kiểm tra địa chỉ
        if (address.isEmpty()) {
            errors.put("address", "Address is required.");
        }

        // Kiểm tra giới tính
        if (gender.isEmpty()) {
            errors.put("gender", "Gender is required.");
        }

        // Kiểm tra ngày sinh và tuổi
        if (birthday == null) {
            errors.put("birthday", "Birthday is required.");
        } else {
            LocalDate today = LocalDate.now();
            int age = Period.between(birthday, today).getYears();
            if (age < 14) {
                errors.put("birthday", "You must be at least 14 years old to register.");
            }
        }

        // Nếu có lỗi, trả về trang đăng ký với các thông báo lỗi
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            model.addAttribute("phoneNumber", phoneNumber);
            model.addAttribute("address", address);
            model.addAttribute("gender", gender);
            model.addAttribute("birthday", birthday);
            return "/signup";
        }

        //change type LocalDate to Date for birthday  then add to newAccount
        java.sql.Date sqlBirthday = java.sql.Date.valueOf(birthday);

        String hashedPassword = passwordEncoder.encode(password);

        // Tạo đối tượng Accounts mới
        Accounts newAccount = new Accounts(0, phoneNumber, hashedPassword, email, 4, gender, fullName, sqlBirthday, address, BigDecimal.ZERO);

        try {
            // Lưu tài khoản mới
            accountsDAO.save(newAccount);

            return "redirect:/login.htm";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred: " + e.getMessage());
            return "/signup";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
