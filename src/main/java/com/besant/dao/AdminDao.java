package com.besant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {

    // Validate admin login
    public boolean ValidateAdmin(String userName, String password)
            throws ClassNotFoundException {
        boolean status = false;
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM adminData WHERE userName=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public int GetDoctorCount()
            throws ClassNotFoundException {

        int count = 0;

        try {

            Connection con =
                    ConnectionClass.GetConnectionDetails();

            String query =
                    "SELECT COUNT(*) FROM doctorData";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}