package com.besant.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.besant.Data.UserData;
import com.besant.dao.UserDao;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
		String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        String contact  = request.getParameter("contact");
        String country  = request.getParameter("country");
        String state    = request.getParameter("state");
        String zip      = request.getParameter("zip");
        
        UserData UserReg = new UserData();
        UserReg.setFullName(fullName);
        UserReg.setUsername(username);
        UserReg.setPassword(password);
        UserReg.setEmail(email);
        UserReg.setContact(contact);
        UserReg.setCountry(country);
        UserReg.setState(state);
        UserReg.setZipCode(zip);
        
        UserDao dao = new UserDao();
        boolean result = dao.InsertUser(UserReg);
        
        PrintWriter out = response.getWriter();
        if (result) {
        	response.sendRedirect("index.html");
        } else {
            out.print("FAIL");
        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        
	}
}
