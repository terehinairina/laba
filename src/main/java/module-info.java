module com.example.words {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.words to javafx.fxml;
    exports com.example.words;
}