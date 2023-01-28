package com.example.inzynieria_sprint_n;

import com.example.inzynieria_sprint_n.password.PasswordUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
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
                try {
                    FileReader fileReader = new FileReader("src/main/java/com/example/inzynieria_sprint_n/password/workers.csv");
                    BufferedReader br = new BufferedReader(fileReader);

                    String line;
                    boolean matchFound = false;
                    while ((line = br.readLine()) != null) {
                        String[] userData = line.split(";");
                        if (username.equals(userData[0]) && PasswordUtils.verifyPassword(password, userData[1])) {
                            matchFound = true;
                            System.out.println("Login successful.");
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
                } catch (IOException e) {
                    e.printStackTrace();
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

        System.out.println(PasswordUtils.hashPassword("makota"));
        launch();
    }

//   TODO synchronizacja metod so singletonu
//   TODO wzorce strukturalne i czynnościowe
//   TODO usuwanie misji na listview po wykonaniu algorytmu
//   TODO chosen mission listview po aktualizacji nie kasuje starych danych xd

}

