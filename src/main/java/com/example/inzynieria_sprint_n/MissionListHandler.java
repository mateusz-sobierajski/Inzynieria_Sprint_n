package com.example.inzynieria_sprint_n;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class MissionListHandler {

    private ListView<Mission> listView;
    private ObservableList<Mission> missionList;
    private final File fileMissionList;
    private final List<Mission> missionArrayList;

    MissionListHandler() throws IOException {

        /*
    MissionListViewHandler(ListView<Mission> listView, List<Mission> missionList) {
        this.listView = listView;
        this.missionList = FXCollections.observableArrayList(missionList);
        listView.setItems(this.missionList);
    }

    public void updateList() {
        listView.setItems(null);
        listView.setItems(missionList);
    }

    public void addMission(Mission mission) {
        missionList.add(mission);
        updateList();
    }

    public void removeMission(Mission mission) {
        missionList.remove(mission);
        updateList();
    }
        */
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
        missionArrayList = new ArrayList<>();
        String line = "";
        String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader(fileMissionList));
        while ((line = br.readLine()) != null) {
            String[] missionDetails = line.split(splitBy);
            missionArrayList.add(new Mission(missionDetails[0], missionDetails[1], missionDetails[2], Boolean.parseBoolean(missionDetails[3])));
        }
    }

    void addRecord(Mission mission) {
        try (FileWriter writer = new FileWriter(fileMissionList, true)) {
            missionArrayList.add(mission);
            writer.write(mission.toString());
            listMissions();
        } catch (IOException e) {
            System.out.println("Blad inicjalizacji zapisu do pliku!");
        }
    }

    void listMissions() {
        for (Mission mission : missionArrayList) {
            System.out.println("Details: [Name=" + mission.getMissionName() + ", Cost=" + mission.getBudgetString() + ", Priority=" + mission.getPriority() + ", IsBlacklisted=" + mission.isBlacklisted() + "]");
        }
    }

}

