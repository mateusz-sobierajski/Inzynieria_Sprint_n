package com.example.inzynieria_sprint_n;

import java.io.*;

public class MissionListHandler {
    private File missionList;

    MissionListHandler() {
        try {
            missionList = new File("proposed_mission_list.csv");
            if (missionList.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            } else {
                System.out.println("Plik już istnieje!");
            }
        } catch (IOException e) {
            System.out.println("Blad tworzenia pliku!!!1!");
            e.printStackTrace();
        }
    }

    int addRecord(String missionName, String budgetString, String priority) {
        try {

            FileWriter writer = new FileWriter(missionList, true);
            //writer.append(missionName + ";" + budgetString + ";" + priority);
            writer.write(missionName + ";" + budgetString + ";" + priority + ";" + false +  "\n");
            writer.close();
            listMissions();
            return 0;
        } catch (IOException e) {
            System.out.println("Blad inicjalizacji zapisu do pliku!");
            return -1;
        }
    }
    String getRecord() {
        return ("a");
    }
    void listMissions() {
        String line = "";
        String splitBy = ";";
        try {

            BufferedReader br = new BufferedReader(new FileReader(missionList));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] missionDetails = line.split(splitBy);    // use comma as separator
                System.out.println("Details: [Name=" + missionDetails[0] + ", Cost=" + missionDetails[1] + ", Priority=" + missionDetails[2] + ", IsBlacklisted=" + missionDetails[3] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO ta klasa będzie tylko obsługiwała plik ze wszystkimi klasmi
    // do wyboru misji przez algotytm będzie nowa
}

