package com.example.inzynieria_sprint_n;

import java.io.*;
import java.nio.CharBuffer;

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
            writer.write(Long.toString(newBudget));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    BudgetManager(){
        try {

            BufferedReader reader = new BufferedReader(new FileReader(budgetFile));
            String currentLine = reader.readLine();
            reader.close();
            System.out.printf(currentLine);
            /*
            FileReader reader = new FileReader(budgetFile);
            CharBuffer budget = null;
            reader.read(budget);
            */
            //System.out.printf(budget);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
