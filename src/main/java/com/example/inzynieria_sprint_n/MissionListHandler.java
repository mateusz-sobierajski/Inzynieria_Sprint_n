package com.example.inzynieria_sprint_n;

import java.io.*;
import java.util.List;

public class MissionListHandler {
    private File fileMissionList;

    private List<Mission> missionList;

    MissionListHandler(List <Mission> missionList) {
        try {
            this.missionList = missionList;
            fileMissionList = new File("proposed_mission_list.csv");
            if (fileMissionList.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            } else {
                System.out.println("Plik już istnieje!");
            }
        } catch (IOException e) {
            System.out.println("Blad tworzenia pliku!!!1!");
            e.printStackTrace();
        }
    }

    int addRecord(Mission mission) {
        try {

            FileWriter writer = new FileWriter(fileMissionList, true);
            //writer.append(missionName + ";" + budgetString + ";" + priority);
            System.out.println(mission.toString());
            writer.write(mission.toString());
            writer.close();
            listMissions();
            return 0;
        } catch (IOException e) {
            System.out.println("Blad inicjalizacji zapisu do pliku!");
            return -1;
        }
    }

    void listMissions() {
        String line = "";
        String splitBy = ";";
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileMissionList));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] missionDetails = line.split(splitBy);    // use comma as separator
                System.out.println("Details: [Name=" + missionDetails[0] + ", Cost=" + missionDetails[1] + ", Priority=" + missionDetails[2] + ", IsBlacklisted=" + missionDetails[3] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    //TODO ta klasa będzie tylko obsługiwała plik ze wszystkimi klasmi
    // do wyboru misji przez algotytm będzie nowa
}

