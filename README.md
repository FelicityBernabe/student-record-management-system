# Student Record Management System

A simple desktop CRUD (Create, Read, Update, Delete) application built using **JavaFX**, **JDBC**, and **PostgreSQL**.
It allows users to manage student records through a clean graphical interface.

---

## Preview
<img width="1919" height="1199" alt="Screenshot 2026-05-28 164929" src="https://github.com/user-attachments/assets/ae5aba79-4d63-4c71-aff0-66c46ad4a099" />

---

## Features

* Add new student records
* View all records in a table
* Update selected records
* Delete records
* Clear input fields
* Sort table columns (ID, Name, Course, Year Level) in ascending or descending order

---

## How to Use

* Enter student details and click **Add**
* Select a row to edit or delete
* Click column headers to sort data (ascending/descending)
* Click **Update** to save changes
* Click **Delete** to remove a record
* Click **Clear** to reset fields

---

## Requirements

* Java JDK 11+
* IntelliJ IDEA
* PostgreSQL
* SceneBuilder (optional)

---

## Setup

### 1. Create Database

Run the following in PostgreSQL:

```sql
CREATE DATABASE student_db;

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    year_level INT NOT NULL
);
```

---

### 2. Configure Database Connection

Edit `DBConnection.java`:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/student_db";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

---

### 3. Run the Application

* Open the project in IntelliJ
* Run `HelloApplication`
* The JavaFX window should appear

---

## Project Structure

```
src/
 └── main/
     ├── java/
     │    └── com.example.sms/
     │         ├── DBConnection.java
     │         ├── HelloApplication.java
     │         ├── HelloController.java
     │         ├── Launcher.java
     │         ├── Student.java
     │         └── YearLevel.java
     │
     └── resources/
          └── com.example.sms/
               ├── main.fxml
               └── style.css
```

---

## Notes

* Data loads automatically on startup
* Avoid leaving input fields empty
