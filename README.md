# MediCore Hospital Management System
MediCore is a web-based Hospital Management System developed using Java Servlets, JDBC, MySQL, HTML, CSS, and JavaScript. The application provides role-based access control for Admins, Doctors, and Patients, allowing efficient management of appointments, patient records, doctor information, and healthcare operations.

## 📌 Project Overview

MediCore is a web-based Hospital Management System developed using Java Servlets, JDBC, MySQL, HTML, CSS, and JavaScript. The application is designed to simplify hospital operations by providing separate modules for Admins, Doctors, and Patients.

The system enables patients to register, log in, browse doctors, book appointments, and view appointment details. Doctors can access their scheduled appointments and patient histories, while administrators can manage doctors, patients, and appointment records through a centralized dashboard.

The project follows a layered architecture using DAO (Data Access Object) design principles, ensuring separation of concerns between the user interface, business logic, and database operations.

# 🎯 Objectives

- Provide role-based access for Admins, Doctors, and Patients.
- Simplify appointment scheduling and management.
- Maintain patient and appointment records efficiently.
- Enable doctor management through an admin dashboard.
- Implement secure login and session management.
- Demonstrate Java Web Application development using Servlets and JDBC.

---

# 🚀 Features

## 👨‍💼 Admin Module

### Authentication
- Secure Admin Login
- Session-based authentication

### Dashboard
- View system statistics
  - Total Appointments
  - Total Patients
  - Total Doctors

### Patient Management
- View all registered patients
- Delete patient records

### Doctor Management
- View all doctors
- Add new doctors
- Delete doctors

### Appointment Management
- View complete appointment history
- Delete appointment records

---

## 👨‍⚕️ Doctor Module

### Authentication
- Secure Doctor Login

### Dashboard Features
- View assigned appointments
- Access patient appointment history
- Manage appointment records

### Appointment Tracking
- View:
  - Patient Name
  - Appointment Date
  - Appointment Time
  - Appointment Status

---

## 👤 Patient Module

### Registration
Patients can create an account by providing:

- Full Name
- Username
- Password
- Email
- Contact Number
- Country
- State
- ZIP Code

### Login
- Secure Patient Login

### Dashboard Features

#### Profile Management
- View personal details
- Update profile information

#### Doctor Browsing
- View available doctors
- View doctor specializations
- View doctor availability

#### Appointment Booking
- Select doctor
- Choose appointment date
- Choose appointment time
- Validate slot availability
- Confirm booking

#### Appointment History
- View all booked appointments
- Track appointment status

#### Appointment Confirmation
- Dynamic appointment confirmation page
- Displays:
  - Appointment ID
  - Patient Name
  - Doctor Name
  - Specialization
  - Appointment Date
  - Appointment Time
  - Booking Status

---

# 🏗️ System Architecture

```text
┌───────────────────┐
│     HTML UI       │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│     Servlets      │
│ Business Logic    │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│       DAO         │
│ Database Access   │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│      MySQL        │
│    Database       │
└───────────────────┘
```

---

# 📂 Project Structure

```text
MediCore
│
├── src
│   │
│   ├── com.besant.Data
│   │     ├── UserData.java
│   │     ├── DoctorData.java
│   │     └── AppointmentData.java
│   │
│   ├── com.besant.dao
│   │     ├── UserDao.java
│   │     ├── DoctorDao.java
│   │     └── AppointmentDao.java
│   │
│   ├── com.besant.servlet
│   │     ├── UserLoginServlet.java
│   │     ├── UserRegisterServlet.java
│   │     ├── DoctorLoginServlet.java
│   │     ├── AdminLoginServlet.java
│   │     ├── BookAppointmentServlet.java
│   │     ├── PatientHistoryServlet.java
│   │     ├── ViewDoctorsServlet.java
│   │     ├── ViewPatientsServlet.java
│   │     ├── DeleteDoctorServlet.java
│   │     ├── DeletePatientServlet.java
│   │     └── DeleteAppointmentServlet.java
│
├── WebContent
│   │
│   ├── HTML Files
│   ├── CSS Files
│   ├── JavaScript Files
│   │
│   ├── index.html
│   ├── Login Pages
│   ├── Dashboard Pages
│   └── Appointment Pages
│
└── Database
      └── MediCore.sql
```

# 🔒 Session Management

The application uses HTTP Sessions to:

- Maintain logged-in user information.
- Store patient details.
- Store doctor information.
- Preserve authentication state.
- Display appointment confirmation data.

---

# 🛠️ Technologies Used

## Backend

- Java
- JDBC
- Java Servlets

## Frontend

- HTML5
- CSS3
- JavaScript

## Database

- MySQL

## Web Server

- Apache Tomcat 9+

## IDE

- Eclipse IDE

---

# ⚙️ Setup Instructions

## 1. Clone Repository

```bash
git clone https://github.com/yourusername/MediCore-Hospital-Management-System.git
```

---

## 2. Create Database

Run:

```sql
CREATE DATABASE MediCore;
```

Stores patient information.

```sql
CREATE TABLE userData(
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(50),
    userName VARCHAR(50),
    password VARCHAR(50),
    email VARCHAR(50),
    contact VARCHAR(15),
    country VARCHAR(50),
    state VARCHAR(50),
    zipCode VARCHAR(10)
);
```

---

## Table: doctorData

Stores doctor information.

```sql
CREATE TABLE doctorData(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(100) NOT NULL,
    userName VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    availability VARCHAR(100)
);
```

---

## Table: appointments

Stores appointment details.

```sql
CREATE TABLE appointments(
    id INT PRIMARY KEY AUTO_INCREMENT,
    patientName VARCHAR(100) NOT NULL,
    userName VARCHAR(50) NOT NULL,
    doctorId INT NOT NULL,
    doctorName VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    appointmentDate DATE NOT NULL,
    appointmentTime TIME NOT NULL,
    status VARCHAR(30) DEFAULT 'Confirmed',
    FOREIGN KEY (doctorId)
    REFERENCES doctorData(id)
    ON DELETE CASCADE
);
```

---

## Table: adminData

Stores admin credentials.

```sql
CREATE TABLE adminData(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

---

## 3. Configure Database Connection

Update database credentials inside the DAO classes:

```java
String url = "jdbc:mysql://localhost:3306/MediCore";
String username = "root";
String password = "yourpassword";
```

---

## 4. Deploy on Tomcat

- Import project into Eclipse
- Configure Apache Tomcat Server
- Add project to Tomcat
- Run Server

---

## 5. Access Application

```text
http://localhost:8080/MediCore/
```

---

# 📈 Future Enhancements

- Password Encryption using BCrypt
- Email Notifications
- Appointment Rescheduling
- Doctor Availability Calendar
- Search & Filter Functionality
- Medical Records Management
- Report Generation
- REST API Integration
- Spring Boot Migration
- Responsive Mobile Design

---

# 📚 Learning Outcomes

This project demonstrates:

- Java Web Development
- Servlet Lifecycle
- JDBC Connectivity
- DAO Design Pattern
- Session Management
- CRUD Operations
- SQL Database Design
- Frontend and Backend Integration
- Role-Based Authentication

---

# 👨‍💻 Author

**S. Nithya Kalyani**
Java Backend Developer

---

# 📄 License

This project is developed for educational and learning purposes.
