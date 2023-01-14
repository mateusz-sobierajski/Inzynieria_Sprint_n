package com.example.inzynieria_sprint_n;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MissionPicker {
    File missionInputs;
    BufferedReader reader;

    Map <String, Integer> budgets = new HashMap<>();

    BudgetManager budgetManager;

    MissionPicker(File missionInputs){
        this.missionInputs = missionInputs;
        try {
            this.reader = new BufferedReader(new FileReader(missionInputs));
        }
        catch(Exception e){
            System.out.println("Blad otwarcia pliku!");
        }
    }
    void getBudget(BudgetManager budgetManager){
        this.budgetManager = budgetManager;
    }
    void readAndPick(){
        try {

            String line = reader.readLine();
            while(line!=null){
                String[] tokens = line.split(";");
                budgets.put(tokens[0], Integer.parseInt(tokens[1]));
                line = reader.readLine();
            }

        }
        catch(Exception e){
            System.out.println("Blad czytania pliku!");
        }

        for (Map.Entry<String, Integer> entry : budgets.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
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
