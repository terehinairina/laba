package com.example.words;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private ImageView amonic;
    @FXML
    private Label active;

    @FXML
    private Button exit;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Button logg;

    @FXML
    private TextField pole1;

    @FXML
    private TextField pole2;


    @FXML
    void initialize() {
        logg.setOnAction(click -> {
            logg.setText("Добро пожаловать");
            logg.getScene().getWindow().hide();
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/session1_1";
                String username = "root";
                String password = "root";
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = DriverManager.getConnection(url, username, password)){

                    Statement statement = conn.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                    while (resultSet.next()) {
                        Integer active = resultSet.getInt(9);
                        String loginDB = resultSet.getString(3);
                        String passDB = resultSet.getString(4);
                        if (passDB.equals(pole2.getText().trim()) && loginDB.equals(pole1.getText().trim()))
                        if( active == 1){ // проверка 1 или
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("hello-view.fxml"));
                                loader.load();
                               Parent root = loader.getRoot();
                                Stage stage = new Stage();
                               stage.setScene(new Scene(root));
                               stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("Верный пароль и акаунт активен ");
                            return;
                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("Error.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("Данные введены корректно, но пользоваетль неактивен");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Connection failed...");
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println("Error");
            }
        });
    }
}