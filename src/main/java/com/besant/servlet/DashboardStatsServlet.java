package com.besant.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.dao.AppointmentDao;
import com.besant.dao.DoctorDao;
import com.besant.dao.UserDao;

@WebServlet("/DashboardStatsServlet")
public class DashboardStatsServlet extends HttpServlet
{
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException
    {
        AppointmentDao appointmentDao =
                new AppointmentDao();

        UserDao userDao =
                new UserDao();

        DoctorDao doctorDao =
                new DoctorDao();

        int appointments =
                appointmentDao.getAppointmentCount();

        int patients =
                userDao.getPatientCount();

        int doctors =
                doctorDao.getDoctorCount();

        response.setContentType("text/plain");

        response.getWriter().print(
                appointments + "," +
                patients + "," +
                doctors);
    }
}