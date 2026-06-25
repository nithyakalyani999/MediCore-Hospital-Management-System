package com.besant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.dao.DoctorDao;

@WebServlet("/DeleteDoctorServlet")
public class DeleteDoctorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int doctorId =
                    Integer.parseInt(
                            request.getParameter("id"));

            DoctorDao dao =
                    new DoctorDao();

            dao.DeleteDoctor(doctorId);

            response.sendRedirect(
                    "ViewDoctorsServlet");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}