package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
//import com.services.AccountService;
import com.services.SessionService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/admin")
public class AccountsController {

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String showAccounts(HttpServletRequest request, ModelMap model) {
        // Kiểm tra phiên làm việc
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }

        // Lấy tham số từ khóa tìm kiếm
        String keyword = request.getParameter("keyword");

        // Lấy tham số trang, mặc định là 1 nếu không có tham số trang
        int page = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

        // Đảm bảo page không nhỏ hơn 1
        if (page < 1) {
            page = 1;
        }

        // Xác định số lượng bản ghi mỗi trang
        int pageSize = 10; // Số lượng bản ghi mỗi trang

        // Nếu keyword null hoặc rỗng, lấy số lượng tài khoản tổng
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = ""; // Đặt keyword thành chuỗi rỗng để tìm tất cả
        }

        // Tính tổng số bản ghi
        int totalRecords = accountsDAO.getTotalAccounts(keyword);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // Nếu không có tài khoản nào, thiết lập trang về 1 và tổng số trang là 1
        if (totalRecords == 0) {
            page = 1;
            totalPages = 1; // Để đảm bảo không có phân trang khi không có tài khoản
        }

        // Lấy danh sách tài khoản với phân trang và tìm kiếm
        List<Accounts> listAccount = accountsDAO.getPagedAccounts(keyword, page, pageSize);
        listAccount.forEach(account -> account.setRoleName(getRoleName(account.getRole())));

        // Thêm các thuộc tính vào model
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword); // Tránh null

        return "/admin/account"; // Trả về trang hiển thị tài khoản
    }

    @RequestMapping(value = "/accountCreate", method = RequestMethod.GET)
    public String showAccountCreateForm(Model model) {
        model.addAttribute("account", new Accounts());
        return "/admin/accountCreate";
    }

    @RequestMapping(value = "/accountCreate.htm", method = RequestMethod.POST)
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
    public String updateAccount(
            @RequestParam("accountID") int id,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
            @ModelAttribute("account") Accounts account,
            BindingResult result,
            Model model) {

        Accounts existingAccount = accountsDAO.findById(id);

        if (existingAccount == null) {
            result.rejectValue("accountID", "error.account", "Account not found.");
            return "/admin/accountUpdate";
        }

        // Validation for birthday
        LocalDate today = LocalDate.now();
        int age = Period.between(birthday, today).getYears();
        if (age < 14) {
            result.rejectValue("birthday", "error.account", "User must be at least 14 years old.");
        }

        if (result.hasErrors()) {
            model.addAttribute("account", account);
            return "/admin/accountUpdate";
        }

        existingAccount.setFullName(account.getFullName());
        existingAccount.setBirthday(java.sql.Date.valueOf(birthday));// đổi kiểu

        // Other fields update
        existingAccount.setEmail(account.getEmail());
        existingAccount.setGender(account.getGender());
        existingAccount.setRole(account.getRole());
        existingAccount.setPhoneNumber(account.getPhoneNumber());
        existingAccount.setHourlyRate(account.getHourlyRate());

        accountsDAO.update(existingAccount);
        return "redirect:/admin/account.htm";
    }

//    @RequestMapping(value = "/export", method = RequestMethod.POST)
//    public ResponseEntity<byte[]> exportAccountsToDoc() {
//        List<Accounts> listAccount = accountsDAO.findAll(); // Lấy tất cả tài khoản
//
//        // Khởi tạo tài liệu và ByteArrayOutputStream
//        XWPFDocument document = new XWPFDocument();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            // Tạo tiêu đề cho file DOC
//            XWPFParagraph title = document.createParagraph();
//            XWPFRun runTitle = title.createRun();
//            runTitle.setText("Account Information");
//            runTitle.setBold(true);
//            runTitle.setFontSize(20);
//            title.setAlignment(ParagraphAlignment.CENTER);
//
//            // Thêm tiêu đề cho các cột
//            XWPFParagraph header = document.createParagraph();
//            XWPFRun runHeader = header.createRun();
//            runHeader.setText("Account ID, Full Name, Phone Number, Email, Role, Gender, Birthday, Address, Hourly Rate");
//            runHeader.addBreak();
//
//            // Thêm dữ liệu tài khoản vào file
//            for (Accounts account : listAccount) {
//                XWPFRun runData = document.createParagraph().createRun();
//                runData.setText(String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s",
//                        account.getAccountID(),
//                        account.getFullName(),
//                        account.getPhoneNumber(),
//                        account.getEmail(),
//                        getRoleName(account.getRole()),
//                        account.getGender(),
//                        account.getBirthday().toString(), // Chuyển đổi LocalDate thành String
//                        account.getAddress(),
//                        account.getHourlyRate()));
//                runData.addBreak();
//            }
//
//            // Ghi tài liệu vào byte array
//            document.write(out);
//
//            // Đặt tiêu đề cho response
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment; filename=accounts.doc");
//
//            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace(); // Xử lý lỗi
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        } finally {
//            // Đóng ByteArrayOutputStream
//            try {
//                out.close(); // Đóng ByteArrayOutputStream
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
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
