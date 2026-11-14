# Pharmacy Management System

A basic desktop-based Pharmacy Management System developed using Java Swing and MySQL. The project provides essential medicine management operations through a graphical user interface.

## Project Overview

The Pharmacy Management System is designed to help an administrator manage medicine records efficiently. It provides login authentication and basic CRUD (Create, Read, Update, Delete) operations for maintaining medicine information.

## Features

- **Admin Login:** Validates administrator credentials using the MySQL database.
- **Dashboard:** Provides access to the main application sections.
- **View Medicines:** Displays medicine records in a table.
- **Add Medicine:** Adds new medicine records to the database.
- **Update Medicine:** Modifies existing medicine information.
- **Delete Medicine:** Deletes selected medicine records after confirmation.
- **Search Medicine:** Searches medicines using their ID or name.
- **View Medicine Details:** Displays the details of a selected medicine.
- **About:** Displays information about the project.

## Technologies Used

- Java (OpenJDK 26)
- Java Swing
- Java AWT
- MySQL
- JDBC (Java Database Connectivity)
- IntelliJ IDEA
- MySQL Connector/J 26.7.0

## Project Structure

```text
PharmacyManagementSystem/
│
├── lib/
│   └── mysql-connector-j-26.7.0.jar
│
├── src/
│   ├── dao/
│   ├── database/
│   ├── images/
│   ├── model/
│   ├── ui/
│   ├── utils/
│   ├── Database.java
│   ├── Main.java
│   └── TestConnection.java
│
├── .gitignore
└── README.md
```

## Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Create a database named `PharmacyDB`.
3. Create the required tables: `Medicines` and `admin`.
4. Configure the database username and password in `DBConnection.java` according to your local MySQL setup.

### Medicines Table

| Column | Data Type |
|---|---|
| id | INT (Primary Key, Auto Increment) |
| name | VARCHAR(100) |
| category | VARCHAR(50) |
| quantity | INT |
| price | DECIMAL(10,2) |
| expiry_date | VARCHAR(20) |

### Admin Table

| Column | Data Type |
|---|---|
| id | INT (Primary Key, Auto Increment) |
| username | VARCHAR(50) |
| password | VARCHAR(100) |

## How to Run the Project

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure JDK 26 is configured.
4. Ensure that MySQL Server is running.
5. Configure the database credentials in `DBConnection.java`.
6. Add the MySQL Connector/J library from the `lib` folder if it is not already configured.
7. Run `Main.java` to start the application.

## Demo Login Credentials

**Username:** `admin`  
**Password:** `pharmacy123`

*Note: These are the local demonstration credentials configured for this project. The admin account must be created in the database before login.*

## Project Scope

This project focuses on basic pharmacy medicine record management through a desktop application. It is developed as an academic project to demonstrate Java Swing, JDBC connectivity, MySQL database operations and basic CRUD functionality.

## Future Scope

- Supplier management
- Sales and billing management
- Low-stock notifications
- Medicine expiry alerts
- Improved user authentication

---

**Developed as an academic project by Manvi Saini.**