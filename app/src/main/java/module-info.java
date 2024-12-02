module com.chinbea.app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chinbea.app to javafx.fxml;
    exports com.chinbea.app;
}