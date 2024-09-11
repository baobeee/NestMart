/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
 *
 * @author Win10
 */
@Service
public class SessionService {

    private static final String[] EXLUDED_PATHS = {"/login", "/signup", "/index"};

    public void createSession(HttpSession session, String email, int role) {
        session.setAttribute("email", email);
        session.setAttribute("role", role);
    }

    public void invalidateSession(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public String getEmail(HttpSession session) {
        return (String) session.getAttribute("email");
    }

    public Integer getRole(HttpSession session) {
        return (Integer) session.getAttribute("role");
    }

    public boolean isSessionValid(HttpServletRequest request) {

        String path = request.getServletPath();
        for (String excludedPath : EXLUDED_PATHS) {
            if (path.startsWith(excludedPath)) {
                return true;
            }
        }

        HttpSession session = request.getSession(false);

        //kiểm tra session có tồn tại and attribute email
        return session == null || session.getAttribute("email") == null;
    }
}
