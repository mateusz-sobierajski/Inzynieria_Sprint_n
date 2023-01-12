package com.example.inzynieria_sprint_n;

import java.io.*;

public class BudgetManager {
    //TODO zdecydować czy zrobić osobną klasę do algorytmu czy wrzucać tu
    private long currentAgencyBudget;
    private final File budgetFile;

    public long getCurrentBudget(){
        return currentAgencyBudget;
    }

    public void setCurrentAgencyBudget(long newBudget) throws IOException {
        currentAgencyBudget = newBudget;
        if (!budgetFile.canWrite()) {
            throw new IOException("Brak dostępu do pliku z budżetem.");
        }
        try (FileWriter writer = new FileWriter(budgetFile)) {
            writer.write(Long.toString(newBudget));
        }
    }
    public BudgetManager() throws IOException {
        budgetFile = new File("budget_file.csv");
        if (!budgetFile.exists()) {
            if (budgetFile.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            }
        }

        if (!budgetFile.canRead()) {
            throw new IOException("Brak dostępu do pliku z budżetem.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(budgetFile))) {
            String currentLine = reader.readLine();
            if (currentLine == null || currentLine.isEmpty()) {
                throw new IOException("Plik z budżetem jest pusty.");
            }
            currentAgencyBudget = Long.parseLong(currentLine);
        }
    }
}
