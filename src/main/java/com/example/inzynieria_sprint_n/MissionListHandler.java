package com.example.inzynieria_sprint_n;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class MissionListHandler {

    private ListView<Mission> listView;
    private ObservableList<Mission> missionList;
    private final File fileMissionList;
    protected final List<Mission> missionArrayList;

    MissionListHandler() throws IOException, URISyntaxException {

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
        URL fileUrl = getClass().getResource("/csv/proposed_mission_list.csv");
        fileMissionList = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();

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

