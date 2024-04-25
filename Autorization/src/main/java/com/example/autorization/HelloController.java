//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.autorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/authorization";
    private static final String USER = "authorization_user";
    private static final String PASSWORD = "your_password_here";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

public class HelloController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public HelloController() {
    }

    @FXML
    protected void ButtonClick() {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        System.out.println("Username: " + username + ", Password: " + password);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Authorization successful!");
                // Здесь можно переключиться на другую сцену
            } else {
                System.out.println("Authorization failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        try{
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("HelloWorld.fxml"));
            //Scene scene = new Scene((Parent)fxmlLoader2.load(), 1000.0, 500.0);
            Parent newPage = fxmlLoader2.load();
            Scene newScene = new Scene(newPage);

            Stage currentStage = (Stage) this.usernameField.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
         */
    }
}
