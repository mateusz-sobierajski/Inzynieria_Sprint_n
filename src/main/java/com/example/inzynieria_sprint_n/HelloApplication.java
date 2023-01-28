package com.example.inzynieria_sprint_n;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * HelloApplication jest klasą, która posiada metodę start(),
 */
public class HelloApplication extends Application {
    /**
     * Funkcja start() przygotowuje i otwiera ekran logowania Login_screen
     * następnie obsługuje logowanie i po podaniu prawidłowych danych przekazuje kontrolę ekranowi zarządzania misjami
     *
     * @param stage
     * @throws IOException
     * @see MissionHandler
     */
    @Override
    public void start(Stage stage) throws IOException {

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

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Please enter a username and password.");
            } else {
                LoginHandler loginHandler = new LoginHandler();
                loginHandler.handleLogin(username, password, stage);
            }
        });
    }

    /**
     * Jedynym celem funkcji main() jest wywołanie komendy launch(), która powoduje otwarcie aplikacji FXML
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

//   TODO synchronizacja metod so singletonu
//   TODO wzorce strukturalne i czynnościowe
//   TODO usuwanie misji na listview po wykonaniu algorytmu

}

