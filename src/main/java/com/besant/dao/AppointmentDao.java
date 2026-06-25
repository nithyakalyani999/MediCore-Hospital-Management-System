package com.besant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.besant.Data.AppointmentData;

public class AppointmentDao {


    public boolean BookAppointment(AppointmentData apt) throws ClassNotFoundException {
        boolean status = false;
        try {
        		if(!isSlotAvailable(
        			apt.getDoctorId(),
        			apt.getAppointmentDate(),
        			apt.getAppointmentTime()))
            {
                return false;
            }
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "INSERT INTO appointments(patientName, userName, doctorId, doctorName, specialization, appointmentDate, appointmentTime, status) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, apt.getPatientName());
            ps.setString(2, apt.getUserName());
            ps.setInt(3,    apt.getDoctorId());
            ps.setString(4, apt.getDoctorName());
            ps.setString(5, apt.getSpecialization());
            ps.setString(6, apt.getAppointmentDate());
            ps.setString(7, apt.getAppointmentTime());
            ps.setString(8, apt.getStatus());
            status = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    public List<AppointmentData> GetUserAppointments(String userName) throws ClassNotFoundException {
        List<AppointmentData> list = new ArrayList<>();
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM appointments WHERE userName=? ORDER BY appointmentDate DESC, appointmentTime DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            list = mapResults(rs);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<AppointmentData> GetDoctorAppointments(String doctorName) throws ClassNotFoundException {
        List<AppointmentData> list = new ArrayList<>();
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM appointments WHERE doctorName=? ORDER BY appointmentDate DESC, appointmentTime DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, doctorName);
            ResultSet rs = ps.executeQuery();
            list = mapResults(rs);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<AppointmentData> GetAllAppointments() throws ClassNotFoundException {
        List<AppointmentData> list = new ArrayList<>();
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "SELECT * FROM appointments ORDER BY appointmentDate DESC, appointmentTime DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            list = mapResults(rs);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean DeleteAppointment(int id) throws ClassNotFoundException {
        boolean status = false;
        try {
            Connection con = ConnectionClass.GetConnectionDetails();
            String query = "DELETE FROM appointments WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            status = ps.executeUpdate() > 0;
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    private List<AppointmentData> mapResults(ResultSet rs) throws Exception {
        List<AppointmentData> list = new ArrayList<>();
        while (rs.next()) {
            AppointmentData a = new AppointmentData();
            a.setId(rs.getInt("id"));
            a.setPatientName(rs.getString("patientName"));
            a.setUserName(rs.getString("userName"));
            a.setDoctorId(rs.getInt("doctorId"));
            a.setDoctorName(rs.getString("doctorName"));
            a.setSpecialization(rs.getString("specialization"));
            a.setAppointmentDate(rs.getString("appointmentDate"));
            a.setAppointmentTime(rs.getString("appointmentTime"));
            a.setStatus(rs.getString("status"));
            list.add(a);
        }
        return list;
    }
    
    public boolean isSlotAvailable(
            int doctorId,
            String appointmentDate,
            String appointmentTime)
    {
        boolean available = true;

        try
        {
        	Connection conn = ConnectionClass.GetConnectionDetails();
            String sql =
                "SELECT id FROM appointments " +
                "WHERE doctorId=? " +
                "AND appointmentDate=? " +
                "AND appointmentTime=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, doctorId);
            ps.setString(2, appointmentDate);
            ps.setString(3, appointmentTime);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                available = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return available;
    }
    
    public int getAppointmentCount()
    {
        int count = 0;

        try
        {
        		Connection con = ConnectionClass.GetConnectionDetails();
            String sql =
                    "SELECT COUNT(*) FROM appointments";

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