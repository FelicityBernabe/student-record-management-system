module com.example.sms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.sms to javafx.fxml;
    exports com.example.sms;
}