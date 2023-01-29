package com.example.inzynieria_sprint_n;

import javafx.stage.Stage;

public class CSVHandler {
    public void updateCSVOnClose(Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.out.println("test");
        });
    }

}
