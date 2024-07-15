package com.jmv.jdbc.rca;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import  jakarta.servlet.http.Cookie;
//import java.io.IOException;
//@WebServlet("/logout")
//public class LogOutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//
//		if (session != null) {
//			session.invalidate();
//		}
//
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("JSESSIONID")) {
//					cookie.setMaxAge(0);
//					response.addCookie(cookie);
//					break;
//				}
//			}
//		}
//
//		request.getRequestDispatcher("/login.jsp").forward(request, response);
//	}
//
//}

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        
        // Prevent caching of sensitive pages
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        // Redirect to login page
        response.sendRedirect("login.jsp");
    }
}

