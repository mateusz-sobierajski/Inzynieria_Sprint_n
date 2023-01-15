package com.example.inzynieria_sprint_n;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

class Product {
    private String name;
    private int price;
    private int cost;
    private String permission;

    public Product(String name, int price, int weight, String permission) {
        this.name = name;
        this.price = price;
        this.cost = weight;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

public class MissionDistributor {
    public static void main(String[] args) throws IOException {
        // Wczytanie pliku CSV
        FileReader fileReader = new FileReader("items.csv");
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
        List<CSVRecord> csvRecords = csvParser.getRecords();
        csvParser.close();

        // Stworzenie listy obiektów na podstawie danych z pliku CSV
        List<Product> products = new ArrayList<>();
        for (CSVRecord csvRecord : csvRecords) {
            int cost = Integer.parseInt(csvRecord.get(1)); //pobieranie kosztow z 2 kolumny
            int priority = Integer.parseInt(csvRecord.get(2)); //pobieranie priorytetu z 3 kolumny
            Product product = new Product(csvRecord.get(0), priority, cost, csvRecord.get(3));
            //if(csvRecord.get(3) == "false")
            products.add(product);
        }

        // Użycie algorytmu plecakowego do wyboru odpowiednich produktów
        int capacity = 22; // zmienić w zaleznosci od budzetu
        List<Product> selectedProducts = knapsack(products, capacity);

        for (Product product : selectedProducts) {
            System.out.println("Name: " + product.getName());
            System.out.println("Cost: " + product.getCost());
            System.out.println("Priority: " + product.getPriority());
            System.out.println("Permission: " + product.getPermission());
            System.out.println("----------------------");
        }


        // Zapis wybranych produktów do nowego pliku CSV
        FileWriter fileWriter = new FileWriter("output.csv");
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
        for (Product product : selectedProducts) {
            csvPrinter.printRecord(product.getName(), product.getCost(), product.getPriority(), product.getPermission());
        }
        csvPrinter.close();
    }

    public static List<Product> knapsack(List<Product> products, int capacity) {
        // Tworzenie tablicy 2D do przechowywania wartości
        int[][] dp = new int[products.size() + 1][capacity + 1];

        // Wypełnianie tablicy wartościami
        for (int i = 1; i <= products.size(); i++) {
            Product product = products.get(i - 1);
            for (int j = 1; j <= capacity; j++) {
                if (product.getCost() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - product.getCost()] + product.getPriority());
                }
            }
        }

        // Odtwarzanie rozwiązania (wybieranie produktów)
        List<Product> selectedProducts = new ArrayList<>();
        int i = products.size();
        int j = capacity;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedProducts.add(0, products.get(i - 1));
                j -= products.get(i - 1).getCost();
            }
            i--;
        }

        return selectedProducts;
    }
}
