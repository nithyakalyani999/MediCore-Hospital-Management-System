package com.besant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.dao.DoctorDao;

@WebServlet("/DoctorLoginServlet")
public class DoctorLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            DoctorDao dao = new DoctorDao();

            boolean status =
                    dao.ValidateDoctor(username, password);

            if(status) {

                HttpSession session =
                        request.getSession();

                session.setAttribute(
                        "doctorUsername",
                        username);

                session.setAttribute(
                        "doctorName",
                        dao.GetDoctorFullName(username));

                response.sendRedirect(
                        "DoctorDashboard.html");

            } else {

                response.getWriter().println(
                        "<h2>Invalid Username or Password</h2>");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}