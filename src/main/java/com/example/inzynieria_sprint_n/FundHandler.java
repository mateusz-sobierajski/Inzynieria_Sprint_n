package com.example.inzynieria_sprint_n;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private void printOutput() throws IOException, URISyntaxException {
        outputText.setText(inputText.getText());
        pieChart.setStartAngle(0);

        String Input = inputText.getText();
        float floatInput = Long.parseLong(Input);

        System.out.println(floatInput);

        //BudgetManager budgetMgr = new BudgetManager();
        //budgetMgr.setCurrentAgencyBudget(120000000);
        budgetMgr = BudgetManager.getInstance();
        /*
        System.out.println(floatInput/budgetMgr.getCurrentBudget());
        System.out.println((budgetMgr.getCurrentBudget()-floatInput)/(budgetMgr.getCurrentBudget()));
        */
        if(floatInput >= budgetMgr.getCurrentBudget()){
            outputText.setText("Budżet misji przerasta budżet agencji!");
            //System.out.println("Budżet misji przerasta budżet agencji!");
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Dodane Fudusze", (floatInput/budgetMgr.getCurrentBudget())),
                        new PieChart.Data("Pozostałe fundusze agencji", ((budgetMgr.getCurrentBudget()-floatInput)/(budgetMgr.getCurrentBudget()))));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Podział funduszy");
        pieChart.setData(pieChartData);
        pieChart.setTitle("Udział funduszy misji w budżecie agencji");
        //pieChart = chart;

    }
}