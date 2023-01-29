package com.example.inzynieria_sprint_n;

import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class CSVHandler {
    public void updateCSVOnClose(Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.out.println();
        });
    }

    public ArrayList<Mission> loadMissionsFromFile(){
        ArrayList<Mission> loadingArray = new ArrayList<>();
        try (Reader reader = new FileReader("src/main/java/com/example/inzynieria_sprint_n/csv/proposed_mission_list.csv");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreEmptyLines(true).withDelimiter(';'))) {
            {
                for (CSVRecord record : csvParser.getRecords()) {
                    loadingArray.add(new Mission(record.get(0), record.get(1), record.get(2), Boolean.parseBoolean(record.get(3))));
                }
                csvParser.close();
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return loadingArray;
    }

}
