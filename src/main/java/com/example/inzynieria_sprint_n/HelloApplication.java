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
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                CSVHandler csvHandler = new CSVHandler();
                csvHandler.updateCSVOnClose(stage);
                LoginHandler loginHandler = new LoginHandler(csvHandler);
                if (loginHandler.handleLogin(username, password)) {
                    try {
                        Parent missionMgmt = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mission_mgmt.fxml")));
                        stage.setScene(new Scene(missionMgmt, 800, 600));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
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
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        databaseConnection.getConnection();
    }

    //      TODO synchronizacja metod so singletonu
//      TODO wzorce strukturalne i czynnościowe

//      TODO drugi algorytm ktory zostal napisany jakos wykorzystac

//      TODO EDYCJA MISJI XD

    //  TODO może mieć dwa pliki pierwszy ze stanem sprzed uruchomienia
    //   i nowy zapisany przy zamknięciu i jak poprawnie zapisze to wyrzucić stary

    //  TODO Czy zapisywac w ogole trzeba chosen missions? jak tak to dodac odczytywanie poprzednio wybranych

    //  TODO DONE >> jak polaczyc loginhandler kotry czyta plik z csvhandlerem XD
//      TODO DONE >> lista misji w singletonie albo jakos bede przekazywal ten obiekt z mission handler zeby w distributor bylo git


}

