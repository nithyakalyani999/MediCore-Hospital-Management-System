package com.besant.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.dao.AppointmentDao;

@WebServlet("/DeleteAppointmentServlet")
public class DeleteAppointmentServlet
        extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int id =
                    Integer.parseInt(
                            request.getParameter("id"));

            AppointmentDao dao =
                    new AppointmentDao();

            dao.DeleteAppointment(id);

            response.sendRedirect(
                    "PatientHistoryServlet");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}