package com.besant.Data;

public class AppointmentData {

    private int    id;
    private String patientName;
    private String userName;
    private int    doctorId;
    private String doctorName;
    private String specialization;
    private String appointmentDate;
    private String appointmentTime;
    private String status;

    public AppointmentData() {}

    public AppointmentData(String patientName, String userName, int doctorId,
                           String doctorName, String specialization,
                           String appointmentDate, String appointmentTime) {
        this.patientName     = patientName;
        this.userName        = userName;
        this.doctorId        = doctorId;
        this.doctorName      = doctorName;
        this.specialization  = specialization;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status          = "Confirmed";
    }

    public int    getId()                              { return id; }
    public void   setId(int id)                        { this.id = id; }

    public String getPatientName()                     { return patientName; }
    public void   setPatientName(String patientName)   { this.patientName = patientName; }

    public String getUserName()                        { return userName; }
    public void   setUserName(String userName)         { this.userName = userName; }

    public int    getDoctorId()                        { return doctorId; }
    public void   setDoctorId(int doctorId)            { this.doctorId = doctorId; }

    public String getDoctorName()                      { return doctorName; }
    public void   setDoctorName(String doctorName)     { this.doctorName = doctorName; }

    public String getSpecialization()                  { return specialization; }
    public void   setSpecialization(String s)          { this.specialization = s; }

    public String getAppointmentDate()                 { return appointmentDate; }
    public void   setAppointmentDate(String d)         { this.appointmentDate = d; }

    public String getAppointmentTime()                 { return appointmentTime; }
    public void   setAppointmentTime(String t)         { this.appointmentTime = t; }

    public String getStatus()                          { return status; }
    public void   setStatus(String status)             { this.status = status; }
}