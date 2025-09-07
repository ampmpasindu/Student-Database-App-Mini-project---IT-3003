# Student-Database-App-Mini-project-IT-3003
index No = s16199 ;
Name = Pasindu Madhusha

# Student Database Management System

A Java application for managing student records with MySQL database integration. This application allows administrators to perform CRUD operations (Create, Read, Update, Delete) on student records using index numbers as the primary identifier.

## Features

- ✅ Add new students with unique index numbers and emails
- ✅ View all students in the database
- ✅ Search students by index number
- ✅ Update student information using index number
- ✅ Delete students using index number
- ✅ Input validation and error handling
- ✅ MySQL database integration with automatic table creation

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
