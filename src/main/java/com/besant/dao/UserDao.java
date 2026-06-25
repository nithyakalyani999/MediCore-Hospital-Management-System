package com.besant.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.besant.Data.UserData;

public class UserDao {
	
	public boolean InsertUser(UserData user) throws ClassNotFoundException, SQLException {

	    try {
	        Connection con = ConnectionClass.GetConnectionDetails();

	        String query = "INSERT INTO userData(fullName, userName, password, email, contact, country, state, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, user.getFullName());
	        ps.setString(2, user.getUsername());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getEmail());
	        ps.setString(5, user.getContact());
	        ps.setString(6, user.getCountry());
	        ps.setString(7, user.getState());
	        ps.setString(8, user.getZipCode());

	        return ps.executeUpdate() > 0;

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean ValidateUser(String userName, String password) throws ClassNotFoundException, SQLException
	{
		boolean status = false;
		try 
		{
			Connection con = ConnectionClass.GetConnectionDetails();
	        String query = "SELECT * FROM userData WHERE userName=? AND password=?";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, userName);
	        ps.setString(2, password);
	        
	        ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();		
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public UserData GetUserProfile(String userName)
	        throws ClassNotFoundException {

	    UserData user = null;

	    try {

	        Connection con =
	                ConnectionClass.GetConnectionDetails();

	        String query =
	                "SELECT * FROM userData WHERE userName=?";

	        PreparedStatement ps =
	                con.prepareStatement(query);

	        ps.setString(1, userName);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()) {

	            user = new UserData();

	            user.setId(rs.getInt("id"));
	            user.setFullName(rs.getString("fullName"));
	            user.setUsername(rs.getString("userName"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            user.setContact(rs.getString("contact"));
	            user.setCountry(rs.getString("country"));
	            user.setState(rs.getString("state"));
	            user.setZipCode(rs.getString("zipCode"));
	        }

	        con.close();

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	
	public boolean deleteUser(int userId)
	{
	    boolean success = false;

	    try
	    {
	    	    Connection conn = ConnectionClass.GetConnectionDetails();
	        String getUserSql =
	                "SELECT userName FROM userData WHERE id=?";

	        PreparedStatement ps1 =
	                conn.prepareStatement(getUserSql);

	        ps1.setInt(1, userId);

	        ResultSet rs = ps1.executeQuery();

	        if(rs.next())
	        {
	            String userName = rs.getString("userName");

	            // Delete appointments
	            String deleteAppointments =
	                    "DELETE FROM appointments WHERE userName=?";

	            PreparedStatement ps2 =
	                    conn.prepareStatement(deleteAppointments);

	            ps2.setString(1, userName);

	            ps2.executeUpdate();

	            // Delete patient
	            String deleteUser =
	                    "DELETE FROM userData WHERE id=?";

	            PreparedStatement ps3 =
	                    conn.prepareStatement(deleteUser);

	            ps3.setInt(1, userId);

	            int rows = ps3.executeUpdate();

	            success = rows > 0;
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return success;
	}
	
	public List<UserData> getAllUsers()
	{
		
		List<UserData> users = new ArrayList<>();

	    try
	    {
	    		Connection conn = ConnectionClass.GetConnectionDetails();
	        String sql = "SELECT * FROM userData";
	        PreparedStatement ps =
	                conn.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next())
	        {
	            UserData user = new UserData();

	            user.setId(rs.getInt("id"));
	            user.setFullName(rs.getString("fullName"));
	            user.setUsername(rs.getString("userName"));
	            user.setEmail(rs.getString("email"));
	            user.setContact(rs.getString("contact"));

	            users.add(user);
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return users;
	}
	
	public int getPatientCount()
	{
	    int count = 0;

	    try
	    {
	    		Connection con = ConnectionClass.GetConnectionDetails();
	        String sql =
	                "SELECT COUNT(*) FROM userData";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ResultSet rs =
	                ps.executeQuery();

	        if(rs.next())
	        {
	            count = rs.getInt(1);
	        }
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }

	    return count;
	}
}
