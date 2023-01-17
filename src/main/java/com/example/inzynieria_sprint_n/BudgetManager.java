package com.example.inzynieria_sprint_n;

import java.io.*;

/**
 * klasa BudgetManager jest singletonem posiadającym i przechowującym budżet całej agencji, którego wartość odczytuje z pliku csv
 */
public class BudgetManager {
    private long currentAgencyBudget;
    private final File budgetFile;

    private static final BudgetManager budgetmanager;

    static {
        try {
            budgetmanager = new BudgetManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Funkcja getInstance zwraca instancję singletonu BudgetManager
     *
     * @return BudgetManager
     */
    public static BudgetManager getInstance() {
        return budgetmanager;
    }

    /**
     * Funkcja getCurrentBUdget zwraca budżet agencji jako wartość long
     *
     * @return currentAgencyBudget
     */
    public long getCurrentBudget() {
        return currentAgencyBudget;
    }

    /**
     * Funkcjia setCurrentAgencyBudget pozwala na zmianę wartości budżetu zapisanego w instancji klasy i pliku csv
     *
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
     *
     * @throws IOException
     */
    private BudgetManager() throws IOException {


        String filePath = "src/main/java/com/example/inzynieria_sprint_n/csv/budget_file.csv";
        File file = new File(filePath);
        this.budgetFile = file;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            if (currentLine == null || currentLine.isEmpty()) {
                throw new IOException("Plik z budżetem jest pusty.");
            }
            currentAgencyBudget = Long.parseLong(currentLine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
