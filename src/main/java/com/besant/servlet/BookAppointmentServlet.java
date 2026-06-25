package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.Data.AppointmentData;
import com.besant.Data.DoctorData;
import com.besant.dao.AppointmentDao;
import com.besant.dao.DoctorDao;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet
        extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            HttpSession session =
                    request.getSession();

            String username =
                    (String)session.getAttribute(
                            "username");

            String patientName =
                    (String)session.getAttribute(
                            "fullName");

            int doctorId =
                    Integer.parseInt(
                            request.getParameter(
                                    "doctorId"));

            String date =
                    request.getParameter(
                            "appointmentDate");

            String time =
                    request.getParameter(
                            "appointmentTime");

            DoctorDao doctorDao =
                    new DoctorDao();

            DoctorData doctor =
                    doctorDao.GetDoctorById(
                            doctorId);

            AppointmentDao dao =
                    new AppointmentDao();

            boolean available =
                    dao.isSlotAvailable(
                            doctorId,
                            date,
                            time);

            if(!available) {

            	response.sendRedirect("UserDashboard.html?error=slotbooked");

                return;
            }

            AppointmentData apt =
                    new AppointmentData();

            apt.setPatientName(patientName);
            apt.setUserName(username);

            apt.setDoctorId(doctorId);

            apt.setDoctorName(
                    doctor.getFullName());

            apt.setSpecialization(
                    doctor.getSpecialization());

            apt.setAppointmentDate(date);

            apt.setAppointmentTime(time);

            apt.setStatus("Confirmed");

            boolean success = dao.BookAppointment(apt);

            if(success)
            {
                session.setAttribute(
                        "appointmentId",
                        "APT" + System.currentTimeMillis());

                session.setAttribute(
                        "patientName",
                        patientName);

                session.setAttribute(
                        "doctorName",
                        doctor.getFullName());

                session.setAttribute(
                        "specialization",
                        doctor.getSpecialization());

                session.setAttribute(
                        "appointmentDate",
                        date);

                session.setAttribute(
                        "appointmentTime",
                        time);

                session.setAttribute(
                        "status",
                        "Confirmed");

                response.sendRedirect(
                        "AppointmentConfirmation.html");
            }
            else
            {
                response.setContentType("text/html");

                PrintWriter out =
                        response.getWriter();

                out.println("<script>");
                out.println("alert('Selected slot is already booked. Please choose another time.');");
                out.println("history.back();");
                out.println("</script>");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}