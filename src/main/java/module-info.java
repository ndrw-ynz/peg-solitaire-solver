module com.example.pegsolitairesolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pegsolitairesolver to javafx.fxml;
    exports com.pegsolitairesolver;
}