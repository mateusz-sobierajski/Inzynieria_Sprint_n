package com.example.inzynieria_sprint_n;

import javafx.css.Size;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

public class MissionPicker {
    File missionInputs;
    BufferedReader reader;

    Map <String, Integer> budgets = new HashMap<>();
    //ArrayList <Integer> budgets = new ArrayList<>();

    MissionPicker(File missionInputs){
        this.missionInputs = missionInputs;
        try {
            this.reader = new BufferedReader(new FileReader(missionInputs));
        }
        catch(Exception e){
            System.out.println("Blad otwarcia pliku!");
        }
    }
    void readAndPick(){
        try {

            String line = reader.readLine();
            //System.out.println(line);
            int i = 0;
            while(line!=null){
                String[] tokens = line.split(";");
                //budgets.add(Integer.parseInt(tokens[1]));
                //System.out.println(tokens[0] + "\n" + (Integer.parseInt(tokens[1])+1) +"\n");
                budgets.put(tokens[0], Integer.parseInt(tokens[1]));
                line = reader.readLine();
                ++i;
            }

        }
        catch(Exception e){
            System.out.println("Blad czytania pliku!");
        }

        for (Map.Entry<String, Integer> entry : budgets.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
/*
        for(Integer i:budgets) {
            System.out.println(i);
        }
*/
    }
    //TODO daty dodania
    //TODO testy
    //TODO dokumentacja UMLe wymagania(diagram przypadków uzycia)
    //TODO diagram klas
    //TODO diagram komponentów jak są pakiety
    //TODO opis dziedziny
    //TODO user stories
    //TODO diagramy klas
    //TODO miejsca ze wzorcami projektowymi
    //TODO wzorce
}
