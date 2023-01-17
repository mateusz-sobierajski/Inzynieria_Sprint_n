package com.example.inzynieria_sprint_n;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * klasa BudgetManager jest singletonem posiadającym i przechowującym budżet całej agencji, którego wartość odczytuje z pliku csv
 */
public class BudgetManager {
    private long currentAgencyBudget;
    private final File budgetFile;

    private static BudgetManager budgetmanager;

    static {
        try {
            budgetmanager = new BudgetManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Funkcja getInstance zwraca instancję singletonu BudgetManager
     * @return BudgetManager
     */
    public static BudgetManager getInstance(){
        return budgetmanager;
    }

    /**
     * Funkcja getCurrentBUdget zwraca budżet agencji jako wartość long
     * @return currentAgencyBudget
     */
    public long getCurrentBudget() {
        return currentAgencyBudget;
    }

    /**
     * Funkcjia setCurrentAgencyBudget pozwala na zmianę wartości budżetu zapisanego w instancji klasy i pliku csv
     * @param newBudget
     * @throws IOException
     */
    public void setCurrentAgencyBudget(long newBudget) throws IOException {
        currentAgencyBudget = newBudget;
        if (!budgetFile.canWrite()) {
            throw new IOException("Brak dostępu do pliku z budżetem.");
        }
        try (FileWriter writer = new FileWriter(budgetFile)) {
            writer.write(Long.toString(newBudget));
        }
    }

    /**
     * Konstruktor klasy BudgetManager tworzy instancję klasy BudgetManager na podstawie zapisanych w pliku pliku csv
     * @throws IOException
     * @throws URISyntaxException
     */
    private BudgetManager() throws IOException, URISyntaxException {

        URL fileUrl = getClass().getResource("/com/example/inzynieria_sprint_n/csv/budget_file.csv");
        File file = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();
        this.budgetFile = file;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            if (currentLine == null || currentLine.isEmpty()) {
                throw new IOException("Plik z budżetem jest pusty.");
            }
            currentAgencyBudget = Long.parseLong(currentLine);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
