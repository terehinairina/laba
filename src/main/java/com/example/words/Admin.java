package com.example.words;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {

    @FXML
    private Button adduser;

    @FXML
    private TableColumn<User, String> age;

    @FXML
    private Button changerole;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, String> lastname;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> office;

    @FXML
    private TableColumn<User, String> userrol;
    @FXML
    private TableView<User> table;
    private Connection connection;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connection = connector.getConnection();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.setItems(connector.getUser("SELECT users.Email, users.FirstName, users.LastName,users.Birthdate, offices.Title, roles.Title FROM users JOIN offices ON users.OfficeID = offices.ID JOIN roles ON users.RoleID = roles.ID"));
        office.setCellValueFactory(new PropertyValueFactory<>("office"));
        userrol.setCellValueFactory(new PropertyValueFactory<>("userrol"));

        adduser.setOnAction(click -> {
            adduser.setText("Добро пожаловать");
            adduser.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("oknoadduser.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        changerole.setOnAction(click -> {
            changerole.setText("Добро пожаловать");
            changerole.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("oknochange.fxml"));
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
