package com.besant.Data;

public class DoctorData {

    private int    id;
    private String fullName;
    private String userName;
    private String password;
    private String specialization;
    private String availability;

    public DoctorData() {}

    public DoctorData(String fullName, String userName, String password,
                      String specialization, String availability) {
        this.fullName       = fullName;
        this.userName       = userName;
        this.password       = password;
        this.specialization = specialization;
        this.availability   = availability;
    }

    public int    getId()                           { return id; }
    public void   setId(int id)                     { this.id = id; }

    public String getFullName()                     { return fullName; }
    public void   setFullName(String fullName)      { this.fullName = fullName; }

    public String getUserName()                     { return userName; }
    public void   setUserName(String userName)      { this.userName = userName; }

    public String getPassword()                     { return password; }
    public void   setPassword(String password)      { this.password = password; }

    public String getSpecialization()               { return specialization; }
    public void   setSpecialization(String s)       { this.specialization = s; }

    public String getAvailability()                 { return availability; }
    public void   setAvailability(String a)         { this.availability = a; }
}