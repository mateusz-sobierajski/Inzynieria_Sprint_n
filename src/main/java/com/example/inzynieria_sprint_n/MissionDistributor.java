package com.example.inzynieria_sprint_n;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MissionDistributor {

    private static MissionDistributor missiondistributor;

    private MissionDistributor() {
    }

    public static MissionDistributor getInstance() {
        if (missiondistributor == null) {
            missiondistributor = new MissionDistributor();
        }
        return missiondistributor;
    }

    CSVHandler csvHandler = new CSVHandler();
    MissionHandler missionHandler = new MissionHandler();

    public List<Mission> chooseMissions(int capacity) throws IOException {

        List<Mission> products = missionHandler.missionArrayList;
        products.removeIf(Mission::isBlacklisted);

        // Użycie algorytmu plecakowego do wyboru odpowiednich produktów
         // zmienić w zaleznosci od budzetu
        List<Mission> selectedProducts = knapsack(products, capacity);

        // Zapis wybranych produktów do nowego pliku CSV
        String chosenMissionsFilePath = "src/main/java/com/example/inzynieria_sprint_n/csv/chosen_mission_list.csv";
        csvHandler.writeMissionToCSV(selectedProducts, chosenMissionsFilePath);

        return selectedProducts;
    }

    public static List<Mission> knapsack(List<Mission> products, int capacity) {
        // Tworzenie tablicy 2D do przechowywania wartości
        int[][] dp = new int[products.size() + 1][capacity + 1];

        // Wypełnianie tablicy wartościami
        for (int i = 1; i <= products.size(); i++) {
            Mission mission = products.get(i - 1);
            for (int j = 1; j <= capacity; j++) {
                if (mission.getBudget() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - (int) mission.getBudget()] + mission.getPriority());
                }
            }
        }

        // Odtwarzanie rozwiązania (wybieranie produktów)
        List<Mission> selectedProducts = new ArrayList<>();
        int i = products.size();
        int j = capacity;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedProducts.add(0, products.get(i - 1));
                j -= products.get(i - 1).getBudget();
            }
            i--;
        }

        return selectedProducts;
    }
    public static List<Mission> knapsackNoPriority(List<Mission> products, int capacity) {
        // Create a 2D array to store values
        int[][] dp = new int[products.size() + 1][capacity + 1];

        // Fill the array with values
        for (int i = 1; i <= products.size(); i++) {
            Mission mission = products.get(i - 1);
            for (int j = 1; j <= capacity; j++) {
                if (mission.getBudget() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - (int) mission.getBudget()] + 1);
                }
            }
        }

        // Reconstruct the solution (choose products)
        List<Mission> selectedProducts = new ArrayList<>();
        int i = products.size();
        int j = capacity;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedProducts.add(0, products.get(i - 1));
                j -= products.get(i - 1).getBudget();
            }
            i--;
        }

        return selectedProducts;
    }

}
