package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.Data.DoctorData;
import com.besant.dao.DoctorDao;

@WebServlet("/AvailableDoctorsServlet")
public class AvailableDoctorsServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {

            DoctorDao dao =
                    new DoctorDao();

            List<DoctorData> doctors =
                    dao.GetAllDoctors();

            PrintWriter out =
                    response.getWriter();

            out.println("<html>");
            out.println("<body>");

            out.println("<h2>Available Doctors</h2>");

            out.println("<table border='1'>");

            out.println(
                    "<tr>" +
                    "<th>Name</th>" +
                    "<th>Specialization</th>" +
                    "<th>Availability</th>" +
                    "<th>Book</th>" +
                    "</tr>");

            for(DoctorData doctor : doctors) {

                out.println("<tr>");

                out.println("<td>"
                        + doctor.getFullName()
                        + "</td>");

                out.println("<td>"
                        + doctor.getSpecialization()
                        + "</td>");

                out.println("<td>"
                        + doctor.getAvailability()
                        + "</td>");

                out.println("<td>");

                out.println(
                        "<a href='BookAppointment.html?id="
                        + doctor.getId()
                        + "'>Book</a>");

                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}