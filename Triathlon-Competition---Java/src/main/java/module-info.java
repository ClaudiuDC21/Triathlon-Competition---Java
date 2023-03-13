module com.example.triathloncompetitionjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.triathloncompetitionjava to javafx.fxml;
    exports com.example.triathloncompetitionjava;
}