package com.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenService {

    private static final int TOKEN_LENGTH = 6; // Length of the verification code

    // Generate a random verification code
    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
    