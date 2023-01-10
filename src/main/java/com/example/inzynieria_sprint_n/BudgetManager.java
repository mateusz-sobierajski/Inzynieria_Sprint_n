package com.example.inzynieria_sprint_n;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BudgetManager {
    //TODO zdecydować czy zrobić osobną klasę do algorytmu czy wrzucać tu
    private long currentAgencyBudget;
    private File budgetFile;

    long getCurrentBudget(){
        return currentAgencyBudget;
    }

    void setCurrentAgencyBudget(long newBudget){
        currentAgencyBudget = newBudget;
        try {
            budgetFile = new File("budget_file.csv");
            if (budgetFile.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            } else {
                System.out.println("Plik już istnieje!");
            }
        } catch (IOException e) {
            System.out.println("Blad tworzenia pliku!!!1!");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(budgetFile);
            writer.write(newBudget);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    BudgetManager(){
        currentAgencyBudget = 0;
    }
}
