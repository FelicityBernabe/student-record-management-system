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
