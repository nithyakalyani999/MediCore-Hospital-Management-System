package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.Data.AppointmentData;
import com.besant.dao.AppointmentDao;

@WebServlet("/MyAppointmentsServlet")
public class MyAppointmentsServlet
        extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {

            HttpSession session =
                    request.getSession();

            String username =
                    (String)session.getAttribute(
                            "username");

            AppointmentDao dao =
                    new AppointmentDao();

            List<AppointmentData> appointments =
                    dao.GetUserAppointments(
                            username);

            PrintWriter out =
                    response.getWriter();

            out.println("<html><body>");

            out.println("<h2>My Appointments</h2>");

            out.println("<table border='1'>");

            out.println(
                    "<tr>" +
                    "<th>Doctor</th>" +
                    "<th>Specialization</th>" +
                    "<th>Date</th>" +
                    "<th>Time</th>" +
                    "<th>Status</th>" +
                    "</tr>");

            for(AppointmentData a : appointments) {

                out.println("<tr>");

                out.println("<td>"
                        + a.getDoctorName()
                        + "</td>");

                out.println("<td>"
                        + a.getSpecialization()
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

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body></html>");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}