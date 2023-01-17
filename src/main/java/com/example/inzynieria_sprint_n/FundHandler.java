package com.example.inzynieria_sprint_n;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FundHandler
{
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField inputText;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private TextArea outputText;

    @FXML
    private PieChart pieChart;

    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    private BudgetManager budgetMgr;

    // Add a public no-args constructor
    public FundHandler()
    {
    }

    @FXML
    private void initialize()
    {
    }

    @FXML
    private void printOutput() {
        outputText.setText(inputText.getText());
        pieChart.setStartAngle(0);

        String Input = inputText.getText();
        float floatInput = Long.parseLong(Input);

        System.out.println(floatInput);

        budgetMgr = BudgetManager.getInstance();
        float budget = budgetMgr.getCurrentBudget();
        if(floatInput >= budget){
            outputText.setText("Budżet misji przerasta budżet agencji!");
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Dodane Fudusze", (floatInput/budget)),
                        new PieChart.Data("Pozostałe fundusze agencji", (budget-floatInput/budget)));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Podział funduszy");
        pieChart.setData(pieChartData);
        pieChart.setTitle("Udział funduszy misji w budżecie agencji");
        //pieChart = chart;

    }
}