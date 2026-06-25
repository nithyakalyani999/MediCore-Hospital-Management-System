package com.besant.Data;

public class UserData {
	
	private int id;
	private String fullName;
    private String username;
    private String password;
    private String email;
    private String contact;
    private String country;
    private String state;
    private String zipCode;
    
    public UserData() {
    }
    
    public UserData(int id,String fullName, String username, String password,
                            String email,    String contact,  String country,
                            String state,    String zipCode) {
    		this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email    = email;
        this.contact  = contact;
        this.country  = country;
        this.state    = state;
        this.zipCode  = zipCode;
    }

    public int getID()          { return id; }
    public void   setId(int id)  { this.id = id; }
    
    public String getFullName()          { return fullName; }
    public void   setFullName(String fullName)  { this.fullName = fullName; }

    public String getUsername()          { return username; }
    public void   setUsername(String username)  { this.username = username; }

    public String getPassword()          { return password; }
    public void   setPassword(String password)  { this.password = password; }

    public String getEmail()             { return email; }
    public void   setEmail(String email)     { this.email = email; }

    public String getContact()           { return contact; }
    public void   setContact(String contact)   { this.contact = contact; }

    public String getCountry()           { return country; }
    public void   setCountry(String country)   { this.country = country; }

    public String getState()             { return state; }
    public void   setState(String state)     { this.state = state; }

    public String getZipCode()           { return zipCode; }
    public void   setZipCode(String zipCode)   { this.zipCode = zipCode; }

}
