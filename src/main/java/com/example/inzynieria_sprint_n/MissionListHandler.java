package com.example.inzynieria_sprint_n;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

public class MissionListHandler {
    private File missionList;

    MissionListHandler() {
        try{
            missionList = new File("proposed_mission_list.csv");
            if(missionList.createNewFile()){
                System.out.println("Plik stworzony prawidłowo");
            }
            else{
                System.out.println("Plik już istnieje!");
            }
        }
        catch(IOException e){
            System.out.println("Blad tworzenia pliku!!!1!");
            e.printStackTrace();
        }
    }

     int addRecord(String missionName, String budgetString, String priority){

        try {
            FileWriter writer = new FileWriter(missionList);
            writer.append(missionName + ";" + budgetString + ";" + priority);
            //writer.write(missionName + ";" + budgetString + ";" + priority);
            writer.close();
            return 0;
        }
        catch (IOException e){
            System.out.println("Blad inicjalizacji zapisu do pliku!");
            return -1;
        }
    }
    String getRecord(){
        return("a");
    }
}
