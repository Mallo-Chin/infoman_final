module com.chinbea.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    opens com.chinbea.app.controllers to javafx.fxml;  // Open to javafx.fxml only for FXML binding
    exports com.chinbea.app.controllers;
    exports com.chinbea.app; // This makes your main app package public, for external use if needed
}
