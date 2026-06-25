package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.besant.Data.UserData;
import com.besant.dao.UserDao;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {

            HttpSession session =
                    request.getSession(false);

            if(session == null) {

                response.sendRedirect("index.html");
                return;
            }

            String username =
                    (String)session.getAttribute(
                            "username");

            UserDao dao =
                    new UserDao();

            UserData user =
                    dao.GetUserProfile(username);

            PrintWriter out =
                    response.getWriter();

            out.println("<html>");
            out.println("<body>");

            out.println("<h2>My Profile</h2>");

            out.println("<table border='1'>");

            out.println("<tr><td>Full Name</td><td>"
                    + user.getFullName()
                    + "</td></tr>");
            
            out.println("<tr><td>User Name</td><td>"
            			+ user.getUsername()
            			+ "</td></tr>");

            out.println("<tr><td>Email</td><td>"
                    + user.getEmail()
                    + "</td></tr>");

            out.println("<tr><td>Contact</td><td>"
                    + user.getContact()
                    + "</td></tr>");

            out.println("<tr><td>Country</td><td>"
                    + user.getCountry()
                    + "</td></tr>");

            out.println("<tr><td>State</td><td>"
                    + user.getState()
                    + "</td></tr>");

            out.println("<tr><td>Zip Code</td><td>"
                    + user.getZipCode()
                    + "</td></tr>");

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}