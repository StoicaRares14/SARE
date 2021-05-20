module SARE {
    requires javafx.fxml;
    requires javafx.controls;

    opens org.example.project to javafx.fxml;
    opens org.example.project.Controllers to javafx.fxml;
    exports org.example.project;
    //exports org.example.project.Actions;
    exports org.example.project.Controllers;
}