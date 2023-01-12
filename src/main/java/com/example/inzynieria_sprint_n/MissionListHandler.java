package com.example.inzynieria_sprint_n;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MissionListHandler {
    private final File fileMissionList;
    private final List<Mission> missionList;

    MissionListHandler() throws IOException {
        fileMissionList = new File("proposed_mission_list.csv");
        if (!fileMissionList.exists()) {
            if (fileMissionList.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            }
        } else {
            System.out.println("Plik już istnieje!");
        }
        if (!fileMissionList.canRead()) {
            throw new IOException("Brak dostępu do pliku z listą misji.");
        }
        missionList = new ArrayList<>();
        String line = "";
        String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader(fileMissionList));
        while ((line = br.readLine()) != null) {
            String[] missionDetails = line.split(splitBy);
            missionList.add(new Mission(missionDetails[0], missionDetails[1], missionDetails[2], Boolean.parseBoolean(missionDetails[3])));
        }
    }

    void addRecord(Mission mission) {
        try (FileWriter writer = new FileWriter(fileMissionList, true)) {
            missionList.add(mission);
            writer.write(mission.toString());
            listMissions();
        } catch (IOException e) {
            System.out.println("Blad inicjalizacji zapisu do pliku!");
        }
    }

    void listMissions() {
        for (Mission mission : missionList) {
            System.out.println("Details: [Name=" + mission.getMissionName() + ", Cost=" + mission.getBudgetString() + ", Priority=" + mission.getPriority() + ", IsBlacklisted=" + mission.isBlacklisted() + "]");
        }
    }
/*
    @FXML
    Pane secPane;

    public void loadFxml(ActionEvent event) throws IOException {
        Pane newLoadedPane = FXMLLoader.load();
        secPane.getChildren().add(newLoadedPane);
    }
*/
}

