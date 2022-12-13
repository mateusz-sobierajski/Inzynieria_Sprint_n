package com.example.inzynieria_sprint_n;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class MissionListHandler {
    private File missionList;

    MissionListHandler() {
        try{
            missionList = new File("com/example/inzynieria_sprint_n/proposed_mission_list.txt");
            if(missionList.createNewFile()){
                System.out.println("Plik stworzony prawidłowo");
            }
            else{
                System.out.println("Plik już istnieje!");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

     int addRecord(String missionName, String budgetString, String priority){

        try {
            FileWriter writer = new FileWriter(missionList);
            writer.write(missionName + ";" + budgetString + ";" + priority);
            return 0;
        }
        catch (IOException e){
            System.out.println("Blad inicjalizacji zapisu do pliku!");
            return -1;
        }
    }
}
