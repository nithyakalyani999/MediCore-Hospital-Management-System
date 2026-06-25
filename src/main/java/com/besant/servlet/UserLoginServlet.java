package com.besant.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.Data.UserData;
import com.besant.dao.UserDao;

@WebServlet("/UserLoginServlet") 
public class UserLoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullName = request.getParameter("");
			
			
			UserDao dao = new UserDao();
	        boolean result = dao.ValidateUser(username, password);
	        
	        if(result) {

	        	UserData user = dao.GetUserProfile(username);

	        	HttpSession session = request.getSession();

	        	session.setAttribute("username", username);

	        	session.setAttribute("fullName", user.getFullName());
	        	response.sendRedirect("UserDashboard.html");

	        } else {

	            request.setAttribute("errorMessage", "Invalid Username or Password");
	            RequestDispatcher rd = request.getRequestDispatcher("index.html");
	            rd.forward(request, response);
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
