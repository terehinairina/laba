package com.example.words;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditRole {

    @FXML
    private RadioButton administrator;

    @FXML
    private Button apply;

    @FXML
    private Button cancel;

    @FXML
    private TextField email;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private ComboBox<String> office;

    @FXML
    private RadioButton user;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();

        ObservableList<String> Office = connector.getOffice("SELECT offices.Title FROM offices");
        office.setValue(Office.get(0));
        for (int i = 0; i < Office.size(); i++) {
            office.getItems().add(String.valueOf(Office.get(i)));
        }

        String selected = office.getValue();

        ToggleGroup group = new ToggleGroup();
        user.setToggleGroup(group);
        administrator.setToggleGroup(group);
        apply.setOnAction(click -> {
            apply.setText("Добро пожаловать");
            apply.getScene().getWindow().hide();
            DBConnector conn = null;
            try {
                conn = new DBConnector();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Connection connection = conn.getConnection();
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ResultSet set = null;
            try {
                set = statement.executeQuery("SELECT offices.ID FROM offices WHERE offices.Title = '" + selected + "'");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                set.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int officeId = 0;
            try {
                officeId = set.getInt("offices.ID");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (user.isSelected()) {
                try {
                    statement.executeUpdate("UPDATE users SET RoleID = 2 WHERE Email = '" + email.getText() + "' AND FirstName = '" + first.getText() + "' AND LastName = '" + last.getText() + "'AND OfficeID = " + officeId + "");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    statement.executeUpdate("UPDATE users SET RoleID = 1 WHERE Email = '" + email.getText() + "' AND FirstName = '" + first.getText() + "' AND LastName = '" + last.getText() + "'AND OfficeID = " + officeId + "");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("oknoadmin.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cancel.setOnAction(click -> {
            cancel.setText("Добро пожаловать");
            cancel.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("oknoadmin.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}