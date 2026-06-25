package com.besant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.besant.Data.DoctorData;

public class DoctorDao {

    // Validate doctor login
    public boolean ValidateDoctor(String userName, String password)
            throws ClassNotFoundException {
        boolean status = false;
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM doctorData WHERE userName=? AND password=?";
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

    // Get doctor full name by username (to store in session)
    public String GetDoctorFullName(String userName) throws ClassNotFoundException {
        String fullName = "";
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT fullName FROM doctorData WHERE userName=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fullName = rs.getString("fullName");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullName;
    }

    // Get all doctors (for Book Appointment page)
    public List<DoctorData> GetAllDoctors() throws ClassNotFoundException {
        List<DoctorData> list = new ArrayList<>();
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM doctorData ORDER BY fullName";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DoctorData d = new DoctorData();
                d.setId(rs.getInt("id"));
                d.setFullName(rs.getString("fullName"));
                d.setUserName(rs.getString("userName"));
                d.setSpecialization(rs.getString("specialization"));
                d.setAvailability(rs.getString("availability"));
                list.add(d);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Add a new doctor (called from admin dashboard)
    public boolean AddDoctor(DoctorData doctor) throws ClassNotFoundException {
        boolean status = false;
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "INSERT INTO doctorData(fullName, userName, password, specialization, availability) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getUserName());
            ps.setString(3, doctor.getPassword());
            ps.setString(4, doctor.getSpecialization());
            ps.setString(5, doctor.getAvailability());
            status = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    

    public DoctorData GetDoctorById(int id) throws ClassNotFoundException {

        DoctorData doctor = null;

        try {

            Connection con = ConnectionClass.GetConnectionDetails();

            String query =
                    "SELECT * FROM doctorData WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                doctor = new DoctorData();

                doctor.setId(rs.getInt("id"));
                doctor.setFullName(rs.getString("fullName"));
                doctor.setUserName(rs.getString("userName"));
                doctor.setPassword(rs.getString("password"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setAvailability(rs.getString("availability"));
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return doctor;
    }
    

    public boolean DeleteDoctor(int doctorId)
            throws ClassNotFoundException {

        boolean status = false;

        try {

            Connection con =
                    ConnectionClass.GetConnectionDetails();

            String query =
                    "DELETE FROM doctorData WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, doctorId);

            status = ps.executeUpdate() > 0;

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public int getDoctorCount()
    {
        int count = 0;

        try
        {
        		Connection con = ConnectionClass.GetConnectionDetails();
            String sql =
                    "SELECT COUNT(*) FROM doctorData";

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