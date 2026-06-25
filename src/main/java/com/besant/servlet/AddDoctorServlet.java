package com.besant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.Data.DoctorData;
import com.besant.dao.DoctorDao;

@WebServlet("/AddDoctorServlet")
public class AddDoctorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            DoctorData doctor =
                    new DoctorData();

            doctor.setFullName(
                    request.getParameter("fullName"));

            doctor.setUserName(
                    request.getParameter("userName"));

            doctor.setPassword(
                    request.getParameter("password"));

            doctor.setSpecialization(
                    request.getParameter("specialization"));

            doctor.setAvailability(
                    request.getParameter("availability"));

            DoctorDao dao =
                    new DoctorDao();

            boolean status =
                    dao.AddDoctor(doctor);

            if(status) {

                response.sendRedirect(
                        "AdminDashboard.html");

            } else {

                response.getWriter().println(
                        "<h2>Doctor Not Added</h2>");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}