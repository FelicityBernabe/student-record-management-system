# Student Record Management System

How to use this:
In IntelliJ, right-click your root project directory.

Select New -> File and name it exactly README.md.

Copy the markdown content below and paste it inside the file.

Markdown
# Student Record Management System

A desktop-based CRUD (Create, Read, Update, Delete) application built using **JavaFX** for the graphical user interface, **JDBC** for database connectivity, and **PostgreSQL** as the relational database management system. This system allows academic administrators to manage student profiles efficiently with a complete visual workflow.

## Features

- **Create**: Add new student entries with validation for Name, Course, and Year Level.
- **Read**: View real-time records populated cleanly from a database into a JavaFX `TableView`.
- **Update**: Select an existing student from the list, modify their details, and save changes seamlessly.
- **Delete**: Remove a student entry directly from the interface and database.
- **User-Friendly UI**: Designed using SceneBuilder, incorporating intuitive text inputs, choice fields, dynamic table selection, and interactive buttons.

## Prerequisites

Before setting up and running the application, ensure you have the following installed on your machine:

- **Java Development Kit (JDK)**: Version 11 or higher.
- **IntelliJ IDEA**: (Community or Ultimate edition).
- **PostgreSQL**: Version 12 or higher.
- **SceneBuilder**: (Optional, for editing FXML layouts).

---

## Installation & Setup

### 1. Database Setup (PostgreSQL)
1. Open **pgAdmin** or your terminal's `psql` client.
2. Create a new database named `student_db`:
   ```sql
   CREATE DATABASE student_db;
Connect to the newly created database and run the following script to generate the students table:

SQL
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    year_level INT NOT NULL
);
2. Project Configuration in IntelliJ
Open IntelliJ IDEA and select Open to import this project directory.

Ensure your build environment maps the necessary PostgreSQL JDBC Driver dependency. If using a Maven framework, verify your pom.xml contains:

XML
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.2</version> </dependency>
3. Update Database Credentials
Navigate to your database connection utility file (e.g., DBConnection.java) and update the connection URL, username, and password fields to match your local PostgreSQL configuration:

Java
private static final String URL = "jdbc:postgresql://localhost:5432/student_db";
private static final String USER = "your_postgres_username";
private static final String PASSWORD = "your_postgres_password";
Running the Application
Locate the main entry point class file containing the application launcher (e.g., MainApp.java).

Right-click the file inside the project explorer hierarchy and select Run 'MainApp.main()'.

The JavaFX GUI stage window should launch.

Project Structure
Plaintext
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── MainApp.java        # Application Entry Point
│   │   │       ├── Controller.java     # Handles FXML UI Logic & Database Queries
│   │   │       ├── Student.java        # Model Class representing Student Records
│   │   │       └── DBConnection.java   # Manages PostgreSQL Connection State
│   │   └── resources/
│   │       └── main.fxml               # SceneBuilder GUI Form Layout
│   └── .gitignore                      # Tells Git which files to ignore
├── pom.xml                             # Project Maven Configuration
└── README.md                           # Project Documentation
UI & Interactions Guide
Populating data: The table initializes automatically upon launching by performing a SELECT query.

Adding records: Fill out the fields and click Add.

Modifying elements: Clicking any row in the TableView automatically fills the input text boxes with that student's details for fast editing or deleting.

Clearing forms: Click Clear to unselect rows and safely reset text fields.
