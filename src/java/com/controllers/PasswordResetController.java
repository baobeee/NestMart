package com.controllers;

import com.models.AccountsDAO;
import com.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Controller
public class PasswordResetController {

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private EmailService emailService;

    // Một lớp để lưu trữ token tạm thời và email người dùng
    private final Map<String, String> tokenStore = new HashMap<>();

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String showForgotPasswordForm() {
        return "forgotPassword";  // Trả về trang để nhập email cần reset mật khẩu
    }

    @RequestMapping(value = "/forgotPassword.htm", method = RequestMethod.POST)
    public String requestResetPassword(@RequestParam("email") String email, Model model) {
        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu không
        boolean emailExists = accountsDAO.existsByEmail(email);

        if (!emailExists) {
            // Nếu email không tồn tại, chuyển đến trang đăng ký
            model.addAttribute("message", "The email address is not registered. Please sign up first.");
            return "signup";  // Trang đăng ký, thay đổi nếu cần
        }

        // Tạo token reset mật khẩu
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, email);

        // Tạo link reset mật khẩu
        String resetLink = "http://localhost:8080/nestmartFinal/resetPassword.htm?token=" + token;

        // Gửi email với link reset
        emailService.sendResetPasswordEmail(email, resetLink);

        model.addAttribute("message", "A password reset link has been sent to your email.");
        return "forgotPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        if (!tokenStore.containsKey(token)) {
            model.addAttribute("message", "Invalid token.");
            return "forgotPassword";  // Trả về trang lỗi nếu token không hợp lệ
        }

        model.addAttribute("token", token);
        return "resetPassword";  // Trả về trang để nhập mật khẩu mới
    }

    @RequestMapping(value = "/resetPassword.htm", method = RequestMethod.POST)
    public String resetPassword(@RequestParam("token") String token, 
                                @RequestParam("password") String newPassword, Model model) {
        if (!tokenStore.containsKey(token)) {
            model.addAttribute("message", "Invalid token.");
            return "forgotPassword";  // Trả về trang lỗi nếu token không hợp lệ
        }

        // Lấy email từ token và đặt lại mật khẩu
        String email = tokenStore.get(token);

        // Hash password trước khi lưu vào cơ sở dữ liệu
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        accountsDAO.updatePassword(email, hashedPassword);

        // Xóa token sau khi đã dùng
        tokenStore.remove(token);

        model.addAttribute("message", "Your password has been reset successfully.");
        return "login";  
    }
}
