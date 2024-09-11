package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import com.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request, HttpSession session, Model model) {

        // Kiểm tra thông tin đăng nhập
        Accounts account = accountsDAO.findByEmail(email);

        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            // Thiết lập session
            session.setAttribute("email", email);
            session.setAttribute("role", account.getRole());

            String redirectUrl;
            switch (account.getRole()) {
                case 1:
                    redirectUrl = "/admin/account.htm";
                    break;
                case 2:
                    redirectUrl = "/employee/index.htm";
                    break;
                case 3:
                    redirectUrl = "/shipper/index.htm";
                    break;
                case 4:
                    redirectUrl = "/customer/index.htm";

                    break;
                default:
                    redirectUrl = "/index.htm";
            }

            // Chuyển hướng đến trang dựa trên vai trò
            sessionService.createSession(session, account.getEmail(), account.getRole());
            return "redirect:" + redirectUrl;
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionService.invalidateSession(session);
        }
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully.");
        return "redirect:/index.htm";
    }
}
