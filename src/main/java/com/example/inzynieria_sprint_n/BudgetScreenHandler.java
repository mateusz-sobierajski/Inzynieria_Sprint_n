package com.example.inzynieria_sprint_n;

/**
 * Klasa handler do komunikacji pomiędzy FXML a innymi klasami, pobiera także dane od fxml
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BudgetScreenHandler {
    @FXML
    private TextField missionNameInput;
    @FXML
    private TextField budgetInput;
    @FXML
    private ListView missionList;

    private MissionListHandler listHandler;

    public BudgetScreenHandler(){

        listHandler = new MissionListHandler();
    }

    @FXML
    public void addMissionClick(){
        String missionName = missionNameInput.getText();
        String budgetString = budgetInput.getText();
        long budgetLong = Long.parseLong(budgetString);
        listHandler.addRecord(missionName, budgetString, "UNP");//UNP - bez nadanego priorytetu
    }

}
