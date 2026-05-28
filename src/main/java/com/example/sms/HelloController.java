package com.example.sms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML private TextField txtName;
    @FXML private TextField txtCourse;
    @FXML private ChoiceBox<String> cbYear;

    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, String> colYear;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private int selectedId = -1; // Track which row ID is selected (-1 means none)

    @FXML
    public void initialize() {
        // 1. Populate the Year Level ChoiceBox dropdown
        cbYear.setItems(FXCollections.observableArrayList(
                "1st Year", "2nd Year", "3rd Year", "4th Year"
        ));

        // 2. Configure table columns to map properties from the Student class
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("yearLevel"));

        // 3. Listen for row clicks in the table to populate form inputs
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedId = newSelection.getId();
                txtName.setText(newSelection.getName());
                txtCourse.setText(newSelection.getCourse());
                cbYear.setValue(newSelection.getYearLevel());
            }
        });

        // 4. Fetch existing records from database
        loadData();
    }

    private void loadData() {
        studentList.clear();
        String query = "SELECT * FROM students ORDER BY id ASC";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Map integer ordinal back to the readable string from our Enum
                int yearOrdinal = rs.getInt("year_level");
                String yearString = YearLevel.values()[yearOrdinal].toString();

                studentList.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        yearString
                ));
            }
            table.setItems(studentList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addStudent() {
        if (txtName.getText().isEmpty() || txtCourse.getText().isEmpty() || cbYear.getValue() == null) {
            showAlert("Validation Error", "All fields are required!");
            return;
        }

        String query = "INSERT INTO students (name, course, year_level) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, txtName.getText());
            pst.setString(2, txtCourse.getText());

            // FIX: Get the numerical position index directly from the dropdown selection
            int yearOrdinal = cbYear.getSelectionModel().getSelectedIndex();
            pst.setInt(3, yearOrdinal);

            pst.executeUpdate();
            loadData();
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateStudent() {
        if (selectedId == -1) {
            showAlert("Selection Error", "Please select a student from the table to update.");
            return;
        }

        String query = "UPDATE students SET name = ?, course = ?, year_level = ? WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, txtName.getText());
            pst.setString(2, txtCourse.getText());

            // FIX: Get the numerical position index directly from the dropdown selection
            int yearOrdinal = cbYear.getSelectionModel().getSelectedIndex();
            pst.setInt(3, yearOrdinal);
            pst.setInt(4, selectedId);

            pst.executeUpdate();
            loadData();
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteStudent() {
        if (selectedId == -1) {
            showAlert("Selection Error", "Please select a student from the table to delete.");
            return;
        }

        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, selectedId);
            pst.executeUpdate();
            loadData();
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {
        txtName.clear();
        txtCourse.clear();
        cbYear.setValue(null);
        table.getSelectionModel().clearSelection();
        selectedId = -1;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}