package com.example.inzynieria_sprint_n;

import java.io.IOException;
import java.io.FileReader;
import java.util.Objects;

import com.example.inzynieria_sprint_n.password.PasswordUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LoginHandler {

    public void handleLogin(String username, String password, Stage stage) {
        try {
            FileReader fileReader = new FileReader("src/main/java/com/example/inzynieria_sprint_n/password/workers.csv");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(';'));

            for (CSVRecord csvRecord : csvParser) {
                String csvUsername = csvRecord.get(0);
                String csvPassword = csvRecord.get(1);

                if (username.equals(csvUsername)) {
                    if (PasswordUtils.verifyPassword(password, csvPassword)) {
                        Parent missionMgmt = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mission_mgmt.fxml")));
                        stage.setScene(new Scene(missionMgmt, 800, 600));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
