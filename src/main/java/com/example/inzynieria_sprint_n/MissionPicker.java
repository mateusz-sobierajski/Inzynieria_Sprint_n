package com.example.inzynieria_sprint_n;

import javafx.css.Size;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Map;

public class MissionPicker {
    File missionInputs;
    BufferedReader reader;

    //Map <String, Integer> budgets;
    ArrayList <String> budgets = new ArrayList<>();

    MissionPicker(File missionInputs){
        this.missionInputs = missionInputs;
        try {
            this.reader = new BufferedReader(new FileReader(missionInputs));
        }
        catch(Exception e){
            System.out.println("Blad otwarcia pliku!");
        }

        //readAndPick();
    }
    void readAndPick(){
        try {

            String line = reader.readLine();
            //System.out.println(line);
            int i = 0;
            while(line!=null){
                String[] tokens = line.split(";");
                //for(String s:tokens) {System.out.println(s);}
                System.out.println("\nWHILE!\n");
                //System.out.println(tokens[1] + " 99999999");
                //budgets.put(tokens[0], Integer.parseInt(tokens[1]));
                budgets.add(tokens[1]);// = tokens[1];
                System.out.println(budgets.get(i));
                line = reader.readLine();
                ++i;
            }

        }
        catch(Exception e){
            System.out.println("Blad czytania pliku!");
        }
/*
        for (Map.Entry<String, Integer> entry : budgets.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
*/
        //System.out.println(budgets[0]);
        //for(String s:budgets) {
        //    System.out.println(s);
        //}
    }
    //TODO daty dodania
    //TODO testy
}
