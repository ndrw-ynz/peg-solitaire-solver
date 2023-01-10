module com.example.pegsolitairesolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pegsolitairesolver to javafx.fxml;
    exports com.example.pegsolitairesolver;
}