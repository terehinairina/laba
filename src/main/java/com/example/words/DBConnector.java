package com.example.words;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBConnector {

    private Connection connection;

    DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/session1_1", "root", "root");
    }

    public ObservableList<User> getUser(String selectRequest) throws SQLException {
        ObservableList<User> res = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String name, lastname, age, userrol, email, office;

        while (set.next()) {
            name = set.getString("users.FirstName");
            lastname = set.getString("users.LastName");
            email = set.getString("users.Email");
            age = set.getString("users.Birthdate");
            userrol = set.getString("offices.Title");
            office = set.getString("roles.Title");
            res.add(new User(name, lastname, age, office, email, userrol));
        }
        return res;
    }
        public ObservableList<String> getOffice (String selectRequest) throws SQLException {
            ObservableList<String> ress = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(selectRequest);

            String nameOffice;

            while (set.next()) {
                nameOffice = set.getString("offices.Title");
                ress.add(nameOffice);
            }
            return ress;
        }
        public Connection getConnection () {
            return connection;
        }
    }
