package com.controllers;

import com.models.Accounts;
import com.models.AccountsDAO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ChangeInforController {

    @Autowired
    private AccountsDAO accountDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Hiển thị thông tin tài khoản
    @RequestMapping(value = "account/accountInformation", method = RequestMethod.GET)
    public String showAccountInformation(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        Integer role = (Integer) session.getAttribute("role");

        if (email == null) {
            model.addAttribute("error", "You must be logged in to view account information.");
            return "redirect:/login.htm";
        }

        Accounts account = accountDAO.findByEmail(email);
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "redirect:/login.htm";
        }

        if (role != null) {
            model.addAttribute("account", account);

            switch (role) {
                case 1:
                    return "/admin/accountInformation";
                case 2:
                    return "/employee/accountInformation";
                case 3:
                    return "/shipper/accountInformation";
                case 4:
                    return "/client/accountInformation";
                default:
                    model.addAttribute("error", "You do not have permission to access this information.");
                    return "redirect:/";
            }
        } else {
            model.addAttribute("error", "You do not have permission to access this information.");
            return "redirect:/";
        }
    }

    // Cập nhật thông tin tài khoản
    @RequestMapping(value = "account/updateInformation", method = RequestMethod.POST)
    public String updateAccountInformation(@ModelAttribute("account") Accounts account,
                                           RedirectAttributes redirectAttributes, HttpSession session) {
        Integer role = (Integer) session.getAttribute("role");

        if (role != null) {
            Accounts existingAccount = accountDAO.findById(account.getAccountID());

            if (existingAccount != null) {
                if (!existingAccount.getPhoneNumber().equals(account.getPhoneNumber())
                        && accountDAO.isPhoneNumberRegistered(account.getPhoneNumber())) {
                    redirectAttributes.addFlashAttribute("error", "Phone number is already registered.");
                    return "redirect:/account/accountInformation.htm";
                }

                existingAccount.setEmail(account.getEmail());
                existingAccount.setGender(account.getGender());
                existingAccount.setPhoneNumber(account.getPhoneNumber());
                existingAccount.setAddress(account.getAddress());
                existingAccount.setFullName(account.getFullName());

                accountDAO.update(existingAccount);
                redirectAttributes.addFlashAttribute("message", "Account information updated successfully.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Account not found.");
            }

            switch (role) {
                case 1:
                    return "redirect:/admin/account.htm";
                case 2:
                    return "redirect:/employee/index.htm";
                case 3:
                    return "redirect:/shipper/shippers.htm";
                case 4:
                    return "redirect:/client/clientboard.htm";
                default:
                    return "redirect:/";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "You do not have permission to update account information.");
            return "redirect:/";
        }
    }

    // Hiển thị trang đổi mật khẩu
    @RequestMapping(value = "account/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            model.addAttribute("error", "You must be logged in to change password.");
            return "redirect:/login.htm";
        }

        Accounts account = accountDAO.findByEmail(email);
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "redirect:/login.htm";
        }

        model.addAttribute("account", account);

        switch (account.getRole()) {
            case 1:
                return "/admin/ChangePassword";
            case 2:
                return "/employee/ChangePassword";
            case 3:
                return "/shipper/ChangePassword";
            case 4:
                return "/client/ChangePassword";
            default:
                model.addAttribute("error", "You do not have permission to change password.");
                return "redirect:/login.htm";
        }
    }

    // Cập nhật mật khẩu
    @RequestMapping(value = "account/changePassword.htm", method = RequestMethod.POST)
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
                                 HttpSession session, RedirectAttributes redirectAttributes,
                                 Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to change password.");
            return "redirect:/login.htm";
        }

        Accounts existingAccount = accountDAO.findByEmail(email);
        if (existingAccount == null) {
            redirectAttributes.addFlashAttribute("error", "Account not found.");
            return "redirect:/login.htm";
        }

        if (!passwordEncoder.matches(oldPassword, existingAccount.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Incorrect old password.");
            return "redirect:/account/changePassword.htm";
        }

        if (newPassword.length() < 6 || !newPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$")) {
            redirectAttributes.addFlashAttribute("error", "Password must be at least 6 characters long and contain both letters and numbers.");
            return "redirect:/account/changePassword.htm";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and confirmation do not match.");
            return "redirect:/account/changePassword.htm";
        }

        existingAccount.setPassword(passwordEncoder.encode(newPassword));
        accountDAO.update(existingAccount);
        redirectAttributes.addFlashAttribute("message", "Password updated successfully.");

        switch (existingAccount.getRole()) {
            case 1:
                return "redirect:/admin/account.htm";
            case 2:
                return "redirect:/employee/index.htm";
            case 3:
                return "redirect:/shipper/shippers.htm";
            case 4:
                return "redirect:/client/clientboard.htm";
            default:
                model.addAttribute("error", "You do not have permission to change password.");
                return "redirect:/login.htm";
        }
    }
}
