package com.example.inzynieria_sprint_n;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MissionListHandler {

    protected final List<Mission> missionArrayList;

    MissionListHandler() throws IOException, URISyntaxException {

        URL fileUrl = getClass().getResource("/csv/proposed_mission_list.csv");
        File fileMissionList = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();

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
}

