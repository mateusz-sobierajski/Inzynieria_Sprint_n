package com.example.inzynieria_sprint_n;

import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    public void updateCSVOnClose(Stage stage) {
        stage.setOnCloseRequest(event -> {
            System.out.println();
        });
    }

    public ArrayList<Mission> loadMissionsFromFile(String filePath){
        ArrayList<Mission> loadingArray = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreEmptyLines(true).withDelimiter(';'))) {
            {
                for (CSVRecord record : csvParser.getRecords()) {
                    Mission missionToAdd = new Mission(record.get(0), record.get(1), record.get(2), Boolean.parseBoolean(record.get(3)));
                    loadingArray.add(missionToAdd);
                }
                csvParser.close();
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return loadingArray;
    }

    public void writeMissionToCSV(List<Mission> missionList, String CSVFilePath) throws IOException {
        FileWriter fileWriter = new FileWriter(CSVFilePath);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withDelimiter(';'));
        for (Mission mission : missionList) {
            csvPrinter.printRecord(mission.getMissionName(), mission.getBudget(), mission.getPriority(), mission.isBlacklisted());
        }
        csvPrinter.close();
    }
}
