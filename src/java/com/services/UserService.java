package com.services;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.mail.MessagingException;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JavaMailSender mailSender;

    private Map<String, String> codeStore = new HashMap<>(); // Store codes temporarily

    // Lưu trữ mã token với thông tin về thời gian hết hạn
    private final Map<String, TokenDetails> tokenStore = new HashMap<>();
    private final int TOKEN_EXPIRATION_MINUTES = 30; // Thời gian hết hạn của mã token (30 phút)

    // Phương thức để tạo mã token reset mật khẩu
    public String createPasswordResetToken(String email) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_MINUTES);
        // Lưu trữ mã token với email và thời gian hết hạn
        tokenStore.put(token, new TokenDetails(email, expirationTime));
        return token;
    }

    // Kiểm tra tính hợp lệ của mã token
    public boolean isValidResetToken(String token) {
        TokenDetails details = tokenStore.get(token);
        if (details == null) {
            return false;
        }
        // Kiểm tra xem mã token có hết hạn hay không
        return LocalDateTime.now().isBefore(details.getExpirationTime());
    }

    // Đặt lại mật khẩu
    public boolean resetPassword(String token, String newPassword) {
        if (!isValidResetToken(token)) {
            return false;
        }

        TokenDetails details = tokenStore.get(token);
        String email = details.getEmail();
        // Thay đổi mật khẩu của người dùng
        // Ví dụ: userRepository.updatePassword(email, newPassword);

        // Xóa token sau khi sử dụng
        tokenStore.remove(token);
        return true;
    }

    // Lớp nội bộ để lưu thông tin về mã token
    private static class TokenDetails {
        private final String email;
        private final LocalDateTime expirationTime;

        public TokenDetails(String email, LocalDateTime expirationTime) {
            this.email = email;
            this.expirationTime = expirationTime;
        }

        public String getEmail() {
            return email;
        }

        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }
    }
}
