package com.besant.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ConfirmationDataServlet")
public class ConfirmationDataServlet extends HttpServlet
{
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException
    {
        HttpSession session =
                request.getSession(false);

        response.setContentType("application/json");

        if(session == null)
        {
            response.getWriter().write("{}");
            return;
        }

        String json =
                "{"
                + "\"appointmentId\":\"" + session.getAttribute("appointmentId") + "\","
                + "\"patientName\":\"" + session.getAttribute("patientName") + "\","
                + "\"doctorName\":\"" + session.getAttribute("doctorName") + "\","
                + "\"specialization\":\"" + session.getAttribute("specialization") + "\","
                + "\"appointmentDate\":\"" + session.getAttribute("appointmentDate") + "\","
                + "\"appointmentTime\":\"" + session.getAttribute("appointmentTime") + "\","
                + "\"status\":\"" + session.getAttribute("status") + "\""
                + "}";

        response.getWriter().write(json);
    }
}