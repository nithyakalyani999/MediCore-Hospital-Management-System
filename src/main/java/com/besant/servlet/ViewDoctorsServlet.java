package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.Data.DoctorData;
import com.besant.dao.DoctorDao;

@WebServlet("/ViewDoctorsServlet")
public class ViewDoctorsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {

            DoctorDao dao =
                    new DoctorDao();

            List<DoctorData> doctors =
                    dao.GetAllDoctors();

            PrintWriter out = response.getWriter();

            for(DoctorData doctor : doctors)
            {
                out.println("<tr>");

                out.println("<td>"
                        + doctor.getId()
                        + "</td>");

                out.println("<td>"
                        + doctor.getFullName()
                        + "</td>");
                out.println("<td>"
                        + doctor.getUserName()
                        + "</td>");

                out.println("<td>"
                        + doctor.getSpecialization()
                        + "</td>");

                out.println("<td>"
                        + doctor.getAvailability()
                        + "</td>");

                out.println("<td>");

                out.println(
                        "<a href='DeleteDoctorServlet?id="
                        + doctor.getId()
                        + "'>Delete</a>");

                out.println("</td>");

                out.println("</tr>");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}