# Student Database App Mini project - IT 3003
index No = s16199 ;
Name = Pasindu Madhusha

A Java application for managing student records with MySQL database integration. This application allows administrators to perform CRUD operations (Create, Read, Update, Delete) on student records using index numbers as the primary identifier.

## Features

-  Add new students with unique index numbers and emails
-  View all students in the database
-  Search students by index number
-  Update student information using index number
-  Delete students using index number
-  Input validation and error handling
-  MySQL database integration with automatic table creation

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or later**
- **MySQL Server 5.7 or later**
- **MySQL Connector/J** (JDBC driver)

## Installation Steps

### 1. Install MySQL Server
Download and install MySQL Server from: https://dev.mysql.com/downloads/mysql/

### 2. Set Up MySQL Database
Run the following SQL commands to create the database and table:

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    indexNo VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    combination VARCHAR(100) NOT NULL
);
```

### 3. Download MySQL Connector/J
Visit: https://dev.mysql.com/downloads/connector/j/

Download the platform-independent ZIP archive

Extract the JAR file (e.g., mysql-connector-j-9.4.0.jar)

(Allready attached the file in repository)

### 4. Set Up the Project
Create a project directory
Place all Java files in the directory:

**GitHub Repository:** [https://github.com/ampmpasindu/student-database-app-mini-project--it-3003](https://github.com/ampmpasindu/student-database-app-mini-project--it-3003)

### Clone the Project

```bash
# Clone the repository
git clone https://github.com/ampmpasindu/student-database-app-mini-project--it-3003
```

# Navigate to project directory
```cmd
cd student-database-app-mini-project--it-3003
```

#### Student.java
#### DatabaseManager.java
#### StudentApp.java

Place the MySQL Connector JAR file in the same directory

Configuration
Database Connection Settings
Update the database connection parameters in StudentApp.java (main method):

java
```java
String url = "jdbc:mysql://localhost:3306/studentdb";
String username = "your mysql username";  // Change this
String password = "your mysql password";  // Change this
```

## Common MySQL Credentials:
Default username: root
Default password: (empty) or the password you set during MySQL installation

### Compilation and Execution

## Compile the application
```cmd
javac -cp ".;mysql-connector-j-9.4.0.jar" *.java
```

## Run the application
```cmd
java -cp ".;mysql-connector-j-9.4.0.jar" StudentApp
```

###Usage Guide

Main Menu Options:
Add Student - Create a new student record
View All Students - Display all students in the database
View Student by Index No - Search for a specific student
Update Student - Modify existing student information
Delete Student - Remove a student from the database
Exit - Close the application

Field Requirements:
Name: Full name (100 characters max, required)

Index Number: Unique identifier (20 characters max, required)
Email: Valid email format (100 characters max, unique, required)
Combination: Subject combination (100 characters max, required)
