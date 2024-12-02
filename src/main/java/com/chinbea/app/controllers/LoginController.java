package com.chinbea.app.controllers;

import com.chinbea.app.utility.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginController {



    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Database database;
    private Connection connection;

    public LoginController() {
        // Initialize the database connection
        database = new Database();
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (database.validateUser(user, pass)) {
            System.out.println("Login Successful!");
            // Proceed to the next stage, e.g., open the dashboard
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
