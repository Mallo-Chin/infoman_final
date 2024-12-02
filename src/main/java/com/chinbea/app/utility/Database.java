package com.chinbea.app.utility;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Config.DB_HOST + ":" + Config.DB_PORT + "/" + Config.DB_NAME, Config.DB_USER, Config.DB_PASS);
            System.out.println("Connection Success!!!");
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void generateUser(String username, String password){
        String query = "INSERT INTO users(username, password) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                return BCrypt.checkpw(password, storedHash); // Compare hashed password
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if validation fails
    }

}
