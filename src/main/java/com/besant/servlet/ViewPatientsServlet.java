package com.besant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.besant.Data.UserData;
import com.besant.dao.UserDao;

@WebServlet("/viewPatients")
public class ViewPatientsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		UserDao dao = new UserDao();

		List<UserData> users = dao.getAllUsers();

		for (UserData user : users) {
			out.println("<tr>");

			out.println("<td>" + user.getID() + "</td>");
			out.println("<td>" + user.getFullName() + "</td>");
			out.println("<td>" + user.getUsername() + "</td>");
			out.println("<td>" + user.getEmail() + "</td>");
			out.println("<td>" + user.getContact() + "</td>");

			out.println("<td>" + "<a href='deletePatient?id=" + user.getID() + "' "
					+ "onclick=\"return confirm('Delete this patient?')\">" + "Delete</a>" + "</td>");

			out.println("</tr>");
		}
	}
}
