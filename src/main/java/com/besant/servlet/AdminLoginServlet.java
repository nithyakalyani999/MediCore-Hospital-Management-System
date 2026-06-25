package com.besant.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.dao.AdminDao;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            AdminDao dao = new AdminDao();
            boolean result = dao.ValidateAdmin(username, password);

            if (result) {
                HttpSession session = request.getSession();
                session.setAttribute("adminUsername", username);
                response.sendRedirect("AdminDashboard.html");
            } else {
                request.setAttribute("errorMessage", "Invalid Username or Password");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}