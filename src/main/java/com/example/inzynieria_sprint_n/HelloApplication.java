package com.example.inzynieria_sprint_n;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login_screen.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Budget_ass_screen.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mission_mgmt.fxml"));

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login_screen.fxml")));
        stage.setTitle("N A S A");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        TextField usernameField = (TextField) root.lookup("#usernameField");
        PasswordField passwordField = (PasswordField) root.lookup("#passwordField");
        Button loginButton = (Button) root.lookup("#loginButton");

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Perform validation on the username and password
            if (username.isEmpty() || password.isEmpty()) {
                // Show an error message if the fields are empty
                System.out.println("Please enter a username and password.");
            } else {
                // Perform the login action
                try {
                    URL fileUrl = getClass().getResource("/csv/workers.csv");
                    File file = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String line;
                    boolean matchFound = false;
                    while ((line = br.readLine()) != null) {
                        String[] userData = line.split(";");
                        if (username.equals(userData[0]) && password.equals(userData[1])) {
                            matchFound = true;
                            System.out.println("Login successful.");
                            //You can add code here to handle a successful login
                            try {
                                Parent missionMgmt = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mission_mgmt.fxml")));
                                stage.setScene(new Scene(missionMgmt, 800, 600));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    if (!matchFound) {
                        System.out.println("Invalid login credentials.");
                    }
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}

