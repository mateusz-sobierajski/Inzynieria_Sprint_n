package com.example.inzynieria_sprint_n;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class BudgetManager {
    private long currentAgencyBudget;
    private final File budgetFile;

    public long getCurrentBudget() {
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

    public BudgetManager() throws IOException, URISyntaxException {

        URL fileUrl = getClass().getResource("/csv/budget_file.csv");
        File file = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();
        this.budgetFile = file;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            if (currentLine == null || currentLine.isEmpty()) {
                throw new IOException("Plik z budżetem jest pusty.");
            }
            currentAgencyBudget = Long.parseLong(currentLine);
        }
    }
}
