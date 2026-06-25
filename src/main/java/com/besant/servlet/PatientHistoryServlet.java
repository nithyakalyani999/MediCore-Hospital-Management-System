package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.Data.AppointmentData;
import com.besant.dao.AppointmentDao;

@WebServlet("/PatientHistoryServlet")
public class PatientHistoryServlet
        extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {

            HttpSession session =
                    request.getSession(false);

            AppointmentDao dao =
                    new AppointmentDao();

            List<AppointmentData> appointments;

            String doctorName =
                    (String)session.getAttribute(
                            "doctorName");

            if(doctorName != null) {

                appointments =
                        dao.GetDoctorAppointments(
                                doctorName);

            } else {

                appointments =
                        dao.GetAllAppointments();
            }

            PrintWriter out =
                    response.getWriter();

            for(AppointmentData a : appointments)
            {
                out.println("<tr>");
                
                out.println("<td>"
                        + a.getId()
                        + "</td>");

                out.println("<td>"
                        + a.getPatientName()
                        + "</td>");

                out.println("<td>"
                        + a.getDoctorName()
                        + "</td>");

                out.println("<td>"
                        + a.getAppointmentDate()
                        + "</td>");

                out.println("<td>"
                        + a.getAppointmentTime()
                        + "</td>");

                out.println("<td>"
                        + a.getStatus()
                        + "</td>");

                if(doctorName == null)
                {
                    out.println(
                        "<td><a href='DeleteAppointmentServlet?id="
                        + a.getId()
                        + "'>Delete</a></td>");
                }
                else
                {
                    out.println("<td>N/A</td>");
                }

                out.println("</tr>");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}