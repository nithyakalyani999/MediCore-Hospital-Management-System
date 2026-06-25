package com.besant.servlet;

import java.io.IOException;
import com.besant.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet
{
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        int id =
                Integer.parseInt(
                        request.getParameter("id"));

        UserDao dao = new UserDao();

        boolean deleted =
                dao.deleteUser(id);

        if(deleted)
        {
            response.sendRedirect(
                    "viewPatients");
        }
        else
        {
            response.sendRedirect(
                    "AdminDashboard.html");
        }
    }
}